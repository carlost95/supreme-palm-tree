package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.ArticuloStockDTO;
import com.undec.corralon.DTO.ArticuloVentaDTO;
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

    public List<ArticuloStockDTO> findByProviderWithStock(Integer idProveedor) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new NotFoundException("Warning: No se encontro al proveedor con id" + idProveedor));

        return this.articuloRepository.findArticulosByProveedorByIdProveedorAndHabilitadoTrue(proveedor)
                .stream()
                .map(this::mapToArticuloStockDTO)
                .collect(Collectors.toList());
    }

    private ArticuloStockDTO mapToArticuloStockDTO(Articulo articulo) {
        ArticuloStockDTO articuloStockDTO = new ArticuloStockDTO();
        try {
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
        if (articulo.getRubroByIdRubro() != null) {
            articuloDTO.setIdRubro(articulo.getRubroByIdRubro().getIdRubro());
        }
        if (articulo.getSubRubroByIdSubRubro() != null) {
            articuloDTO.setIdRubro(articulo.getSubRubroByIdSubRubro().getIdSubRubro());
        }

        return articuloDTO;

    }

    public List<Articulo> listAllArticlesEnabled() {
        List<Articulo> articulos = articuloRepository.findArticuloByHabilitadoEquals(true);
        if (articulos.isEmpty())
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
            throw new BadRequestException("\nWARNING: Los datos requeridos del articulo no pueden estar vacios");
        if (duplicationArticle(articulo))
            throw new BadRequestException("\nWARNING: El articulo que intenta cargar ya exite");

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
        validateArticleByCodigoAndId(articuloDTO);
        articulo = mappedDTOToEntity(articuloDTO, articulo);
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

    private void validateArticleByCodigoAndId(ArticuloDTO articuloDTO) {
        if (articuloRepository.existsArticuloByCodigoAndIdArticuloNot(articuloDTO.getCodigoArt(), articuloDTO.getId()))
            throw new BadRequestException("\nWARNING: El codigo "
                    + articuloDTO.getCodigoArt()
                    + " ya se encuentra registrado en otro articulo");
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
        if (articuloDTO.getIdUnidadMedida().toString().isEmpty())
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, la unidad de medida no puede ser null");

        if (articuloDTO.getIdProveedor().toString().isEmpty())
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, el proveedor no puede ser null");

        if (articuloDTO.getIdMarca().toString().isEmpty())
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, la marca no puede ser null");

        if (articuloDTO.getIdRubro().toString().isEmpty())
            throw new BadRequestException("\nWARNING: Error en los datos de articulo, el rubro no puede ser null");

        Articulo articulo = new Articulo();
        mappedDTOToEntity(articuloDTO, articulo);
        return articulo;
    }

    private Articulo mappedDTOToEntity(ArticuloDTO articuloDTO, Articulo articulo) {
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
        return articulo;
    }

    private boolean duplicationArticle(Articulo articulo) {
        return articuloRepository.existsByCodigo(articulo.getCodigo());

    }

    private boolean validationArticle(Articulo articulo) {
        return (articulo.getNombre().isEmpty() ||
                articulo.getAbreviatura().isEmpty() ||
                articulo.getCodigo().isEmpty()) ? true : false;
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
        CostoArticulo costo = costoRepository.findCostoArticuloByIdArticulo(articulo);

        if (costo == null)
            new NotFoundException("\nWARNING: No existe costo, no se puede actualizar");

        if (articuloDTO.getCosto().compareTo(costo.getCosto()) == 0) {
            return costo;
        }
        costo.setFechaHasta(fechaActual);
        costoRepository.save(costo);
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

        if (articuloDTO.getPrecio().compareTo(precio.getPrecio()) == 0) {
            return precio;
        }

        precio.setFechaHasta(fechaActual);
        precioRepository.save(precio);
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

    public List<ArticuloVentaDTO> obtenerArticulosVenta() {
        return this.articuloRepository
                .findArticulosByHabilitadoIsTrue()
                .stream()
                .map( this::obtenerArticuloVenta )
                .collect(Collectors.toList());
    }

    private ArticuloVentaDTO obtenerArticuloVenta(Articulo articulo) {
        ArticuloVentaDTO articuloVenta = new ArticuloVentaDTO();
        articuloVenta.setIdArticulo(articulo.getIdArticulo());
        articuloVenta.setNombre(articulo.getNombre());
        articuloVenta.setCodigoArticulo(articulo.getCodigo());
        articuloVenta.setPrecio(articulo.getPrecio());
        return articuloVenta;
    }
}
