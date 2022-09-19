package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.ArticuloStockDTO;
import com.undec.corralon.Util;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    MovimientoArticuloRepository movimientoArticuloRepository;


    public List<ArticuloDTO> listAllArticles() {
        return articuloRepository
                .findAll().stream()
                .map(this::mapEntityDTO)
                .collect(Collectors.toList());
    }

    public List <ArticuloStockDTO> findByProviderWithStock(Integer idProveedor){
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor)
                .orElseThrow( () -> new NotFoundException("Warning: No se encontro al proveedor con id" + idProveedor));

        return this.articuloRepository.findArticulosByProveedorByIdProveedorAndHabilitadoTrue(proveedor)
                .stream()
                .map(this::mapToArticuloStockDTO)
                .collect(Collectors.toList());
    }

    private ArticuloStockDTO mapToArticuloStockDTO( Articulo articulo) {
        ArticuloStockDTO articuloStockDTO = new ArticuloStockDTO();
        try{
            String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Date fecha = Util.stringToDate(fechaActual);
            Double stock = this.movimientoArticuloRepository.stockPorArticulo(articulo, fecha);
            articuloStockDTO.setId(articulo.getIdArticulo());
            articuloStockDTO.setStockActual(stock == null ? 0 : stock);
            articuloStockDTO.setCodigoArt(articulo.getCodigo());
            articuloStockDTO.setNombre(articulo.getNombre());
            articuloStockDTO.setIdProveedor(articulo.getProveedorByIdProveedor().getIdProveedor());
        } catch (Exception e) {
            System.out.println("Error al mapear articulo a dto");
        }
        return articuloStockDTO;
    }

    private ArticuloDTO mapEntityDTO(Articulo articulo) {
        ArticuloDTO articuloDTO = new ArticuloDTO();
        articuloDTO.setId(articulo.getIdArticulo());
        articuloDTO.setNombre(articulo.getNombre());
        articuloDTO.setAbreviatura(articulo.getAbreviatura());
        articuloDTO.setCodigoArt(articulo.getCodigo());
        articuloDTO.setStockMin(articulo.getStockMinimo());
        articuloDTO.setStockMax(articulo.getStockMaximo());
        articuloDTO.setCosto(articulo.getCosto());
        articuloDTO.setPrecio(articulo.getPrecio());
        articuloDTO.setHabilitado(articulo.getHabilitado());
        articuloDTO.setIdProveedor(articulo.getProveedorByIdProveedor().getIdProveedor());
        articuloDTO.setIdUnidadMedida(articulo.getUnidadMedidaByIdUnidadMedida().getIdUnidadMedida());
        articuloDTO.setIdMarca(articulo.getMarcaByIdMarca().getIdMarca());
        if(articulo.getRubroByIdRubro() != null){
            articuloDTO.setIdRubro(articulo.getRubroByIdRubro().getIdRubro());
        }
        if(articulo.getSubRubroByIdSubRubro() != null){
            articuloDTO.setIdRubro(articulo.getSubRubroByIdSubRubro().getIdSubRubro());
        }

        return articuloDTO;

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

        if (saveCostoArticle(articulo, articuloDTO) == null)
            throw new BadRequestException("\nWARNING: Error en la carga de costos");

        if (savePrecioArticle(articulo, articuloDTO) == null)
            throw new BadRequestException("\nWARNING: Error en la carga de precio");

        return articulo;
    }

    public Articulo updatedTheArticle(ArticuloDTO articuloDTO) {
        Articulo articulo = articuloRepository.findById(articuloDTO.getId()).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: Error no existe el articulo"));
        mappedDTOToEntity(articuloDTO, articulo);
        articulo.setHabilitado(articuloDTO.getHabilitado());
        articulo = articuloRepository.save(articulo);

        if (articulo == null)
            throw new NotFoundException("\nWARNING: Error en la actualizacion del articulo");

        if (updatedCostoArticle(articulo, articuloDTO) == null)
            throw new BadRequestException("\nWARNING: Error en la carga de costos");
        if (updatedPrecioArticle(articulo, articuloDTO) == null)
            throw new BadRequestException("\nWARNING: Error en la carga de precio");
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
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setCosto(articuloDTO.getCosto());

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
        return false;
    }

    private CostoArticulo saveCostoArticle(Articulo articulo, ArticuloDTO articuloDTO) {
        String fechaActual = String.valueOf(LocalDateTime.now());
        CostoArticulo costo = new CostoArticulo();

        if (articuloDTO.getCosto() == null)
            throw new BadRequestException("\nWARNING: No se puede cargar un articulo sin costo");

        costo.setCosto(articuloDTO.getCosto());
        costo.setFechaDesde(fechaActual);
        costo.setFechaHasta(null);
        costo.setArticuloByIdArticulo(articulo);
        costo = costoRepository.save(costo);

        if (costo == null)
            throw new NotFoundException("\nWARNING: Error en la carga de costo del costo al articulo");

        return costo;
    }

    private PrecioArticulo savePrecioArticle(Articulo articulo, ArticuloDTO articuloDTO) {
        String fechaActual = String.valueOf(LocalDateTime.now());
        PrecioArticulo precio = new PrecioArticulo();

        if (articuloDTO.getPrecio() == null)
            throw new BadRequestException("\nWARNING: No se puede cargar un articulo sin precio");

        precio.setPrecio(articuloDTO.getPrecio());
        precio.setFechaDesde(fechaActual);
        precio.setFechaHasta(null);
        precio.setArticuloByIdArticulo(articulo);
        precio = precioRepository.save(precio);

        if (precio == null)
            throw new NotFoundException("\nWARNING: Error en la carga de precio para el articulo");

        return precio;
    }

    private CostoArticulo updatedCostoArticle(Articulo articulo, ArticuloDTO articuloDTO) {
        String fechaActual = String.valueOf(LocalDateTime.now());
        CostoArticulo costo = costoRepository.findCostoArticuloByIdArtculo(articulo);

        if (costo == null)
            new NotFoundException("\nWARNING: No existe costo, no se puede actualizar");

        if (articuloDTO.getCosto().compareTo(costo.getCosto())==0) {
            return costo;
        }
        costo.setFechaHasta(fechaActual);
        costoRepository.save(costo);
//            new costo date in article
        CostoArticulo costoSave = new CostoArticulo();
        costoSave.setCosto(articuloDTO.getCosto());
        costoSave.setFechaDesde(fechaActual);
        costoSave.setFechaHasta(null);
        costoSave.setArticuloByIdArticulo(articulo);
        costoSave = costoRepository.save(costoSave);

        if (costoSave == null)
            throw new NotFoundException("\nWARNING: Error al actualizar el costo del articulo");

        return costoSave;
    }

    private PrecioArticulo updatedPrecioArticle(Articulo articulo, ArticuloDTO articuloDTO) {
        String fechaActual = String.valueOf(LocalDateTime.now());
        PrecioArticulo precio = precioRepository.findPrecioArticuloByIdArtculo(articulo);

        if (precio == null)
            new NotFoundException("\nWARNING: No existe precio, no se puede actualizar");

        if (articuloDTO.getPrecio().compareTo(precio.getPrecio())== 0) {
            return precio;
        }

        precio.setFechaHasta(fechaActual);
        precioRepository.save(precio);
//            new precio date in article
        PrecioArticulo precioSave = new PrecioArticulo();
        precioSave.setPrecio(articuloDTO.getPrecio());
        precioSave.setFechaDesde(fechaActual);
        precioSave.setFechaHasta(null);
        precioSave.setArticuloByIdArticulo(articulo);
        precioSave = precioRepository.save(precioSave);

        if (precioSave == null)
            throw new NotFoundException("\nWARNING: Error al actualizar el precio del articulo");

        return precioSave;
    }
}
