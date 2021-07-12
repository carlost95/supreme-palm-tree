package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.articulo.ArticuloErrorToDeleteException;
import com.undec.corralon.excepciones.articulo.ArticuloErrorToSaveException;
import com.undec.corralon.excepciones.articulo.ArticuloErrorToUpdateException;
import com.undec.corralon.excepciones.articulo.ArticuloException;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.tipoDireccion.TipoDireccionListNotFoundException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.CostoArticulo;
import com.undec.corralon.modelo.PrecioArticulo;
import com.undec.corralon.modelo.SubRubro;
import com.undec.corralon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    RubroRepository rubroRepository;

    @Autowired
    SubRubroRepository subRubroRepository;

    @Autowired
    PrecioRepository precioRepository;

    @Autowired
    CostoRepository costoRepository;

    @Autowired

    public List<Articulo> listAllArticles() {
        List<Articulo> articulos = articuloRepository.findAll();
        if (articulos == null)
            throw new NotFoundException("\nWARNING: No existen articulos en la DB");
        return articulos;
    }

    public List<Articulo> listAllArticlesEnabled() {
        List<Articulo> articulos = articuloRepository.findArticuloByHabilitadoEquals(true);
        if (articulos == null)
            throw new NotFoundException("\nWARNING: No existen articulos habilitados");

        return articulos;
    }

    public Articulo findArticleById(Integer id) {
        Articulo articulo = articuloRepository.findById(id).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe el articulo en la BD"));
        return articulo;
    }

    public Articulo saveArticle(ArticuloDTO articuloDTO) {
        Articulo articulo = articuloDTOToEntity(articuloDTO);

        if (validationArticle(articulo))
            throw new BadRequestException("\nWARNING: Los datos requeridos del articulo son null");
        if (duplicationArticle(articulo))
            throw new BadRequestException("\nWARNING: No se puede cargar articulos duplicados");

        if (articulo == null)
            throw new NotFoundException("\nWARNING: Error al guardar el articulo");

        articulo.setHabilitado(true);
        articulo = articuloRepository.save(articulo);
        if (articulo == null)
            throw new NotFoundException("\nWARNING: Error en la carga de articulo");

        if (!saveDateArticle(articulo, articuloDTO))
            throw new BadRequestException("\nWARNING: Error en la carga de costos y precio");

        return articulo;
    }

    public Articulo updatedTheArticle(ArticuloDTO articuloDTO) {
        Articulo articulo = articuloRepository.findById(articuloDTO.getId())
                .orElseThrow(
                        () -> new NotFoundException("\nWARNING: Error no existe el articulo"));
        mappedDTOToEntity(articuloDTO, articulo);
        articulo.setHabilitado(articuloDTO.getHabilitado());
        articulo = articuloRepository.save(articulo);

        if (articulo == null)
            throw new NotFoundException("\nWARNING: Error en la actualizacion dl articulo");

        if (!saveDateArticle(articulo, articuloDTO))
            throw new BadRequestException("\nWARNING: Error en la carga de costos y precio");

        return articulo;
    }

    public Articulo changeTheHabilitation(Integer id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("\nWARNING: No existe el id del articulo que se desea cambiar de estado"));

        articulo.setHabilitado(!articulo.getHabilitado());
        articulo = articuloRepository.save(articulo);

        if (articulo == null)
            throw new NotFoundException("\nWARNING: Error no existe el acticulo para el cambio de estado");

        return articulo;
    }

    private Articulo articuloDTOToEntity(ArticuloDTO articuloDTO) {
        if (articuloDTO.getIdUnidadMedida() == null)
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, la unidad de medida no puede ser null");

        if (articuloDTO.getIdProveedor() == null)
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, el proveedor no puede ser null");

        if (articuloDTO.getIdMarca() == null)
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, la marca no puede ser null");

        if (articuloDTO.getIdRubro() == null)
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, el rubro no puede ser null");

        Articulo articulo = new Articulo();
        mappedDTOToEntity(articuloDTO, articulo);
        return articulo;
    }

    private void mappedDTOToEntity(ArticuloDTO articuloDTO, Articulo articulo) {
        articulo.setNombre(articuloDTO.getNombre());
        articulo.setAbreviatura(articuloDTO.getAbreviatura());
        articulo.setCodigo(articuloDTO.getCodigoArt());
        articulo.setStockMinimo(articuloDTO.getStockMin());
        articulo.setStockMaximo(articuloDTO.getStockMax());

        if (articuloDTO.getIdProveedor() != null) {
            articulo.setProveedorByIdProveedor(proveedorRepository.findById(articuloDTO.getIdProveedor())
                    .orElseThrow(
                            () -> new NotFoundException("\nWARNING: No existe el proveedor por id en la carga de articulo")));
        }

        if (articuloDTO.getIdUnidadMedida() != null)
            articulo.setUnidadMedidaByIdUnidadMedida(unidadMedidaRepository.findById(articuloDTO.getIdUnidadMedida())
                    .orElseThrow(
                            () -> new NotFoundException("\nWARNING: Error no existe unidad de medida por id en la carga de articulo")));

        if (articuloDTO.getIdMarca() != null)
            articulo.setMarcaByIdMarca(marcaRepository.findById(articuloDTO.getIdMarca())
                    .orElseThrow(
                            () -> new NotFoundException("\nWARNING: Error no existe marca por id en la carga de articulo")));

        if (articuloDTO.getIdRubro() != null)
            articulo.setRubroByIdRubro(rubroRepository.findById(articuloDTO.getIdRubro())
                    .orElseThrow(
                            () -> new NotFoundException("\nWARNING: Error no existe rubro por id en la carga de articulo")));

        if (articuloDTO.getIdSubRubro() != null)
            articulo.setSubRubroByIdSubRubro(subRubroRepository.findById(articuloDTO.getIdSubRubro()).get());

    }

    private boolean duplicationArticle(Articulo articulo) {
        return articuloRepository.existsByNombreOrAbreviaturaOrCodigo(
                articulo.getNombre(), articulo.getAbreviatura(), articulo.getCodigo());

    }

    private boolean validationArticle(Articulo articulo) {
        if (articulo.getNombre() == null || articulo.getAbreviatura() == null)
            return true;
        else return false;
    }

    private boolean saveDateArticle(Articulo articulo, ArticuloDTO articuloDTO) {
        PrecioArticulo precio = new PrecioArticulo();
        CostoArticulo costo = new CostoArticulo();
        String fechaActual = String.valueOf(LocalDateTime.now());

        if (articuloDTO.getPrecio() == null)
            throw new BadRequestException("\nWARNING: No se puede cargar un articulo sin precio");
        if (articuloDTO.getCosto() == null)
            throw new BadRequestException("\nWARNING: No se puede cargar un articulo sin costo");

        precio.setPrecio(articuloDTO.getPrecio());
        precio.setFechaDesde(fechaActual);
        precio.setFechaHasta(null);
        precio.setArticuloByIdArticulo(articulo);

        costo.setCosto(articuloDTO.getCosto());
        costo.setFechaDesde(fechaActual);
        costo.setFechaHasta(null);
        costo.setArticuloByIdArticulo(articulo);

        precio = precioRepository.save(precio);
        costo = costoRepository.save(costo);

        if (precio == null)
            throw new NotFoundException("\nWARNING: error al almacenar precio de articulo");

        if (costo == null)
            throw new NotFoundException("\nwarning: error al almacenar copsto de articulo");
        if (costo != null && precio != null)
            return true;
        return false;
    }
}
