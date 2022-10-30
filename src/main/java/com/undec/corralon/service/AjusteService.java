package com.undec.corralon.service;

import com.undec.corralon.DTO.AjusteDTO;
import com.undec.corralon.DTO.ArticuloStockDTO;
import com.undec.corralon.DTO.DetalleTipoMovimientoDTO;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.AjusteRepository;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.DetalleAjusteRepository;
import com.undec.corralon.repository.MovimientoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AjusteService {
    @Autowired
    AjusteRepository ajusteRepository;

    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    DetalleAjusteRepository detalleAjusteRepository;

    @Autowired
    MovimientoArticuloService movimientoArticuloService;
    @Autowired
    MovimientoArticuloRepository movimientoArticuloRepository;

    public List<Ajuste> findAllTheSetting() {
        List<Ajuste> ajustes = this.ajusteRepository.findAll();
        if (ajustes == null) throw new NotFoundException("\nWARNING: Error no exiten ajustes");
        return ajustes;
    }


    public AjusteDTO findSettingById(Integer id) {

        AjusteDTO ajusteDTO = this.ajusteRepository
                .findById(id)
                .map(order -> this.ajusteToAjusteDTO(order))
                .orElseThrow(
                        () -> new NotFoundException("Warning: no existe el ajuste con el identificador" + id)
                );
        return ajusteDTO;
    }

    private AjusteDTO ajusteToAjusteDTO(Ajuste ajuste) {
        AjusteDTO ajusteDTO = new AjusteDTO();
        ajusteDTO.setId(ajuste.getIdAjuste());
        ajusteDTO.setNombre(ajuste.getNombre());
        ajusteDTO.setDescripcion(ajuste.getDescripcion());
        ajusteDTO.setFecha(ajuste.getFecha());
        List <ArticuloStockDTO> articulos = this.detalleAjusteRepository
                .findByAjusteByIdAjuste(ajuste)
                .stream()
                .map(detalle->{
                    ArticuloStockDTO articuloStockDTO = this.mapToArticuloStockDTO(detalle.getArticuloByIdArticulo(), ajuste.getFecha());
                    articuloStockDTO.setCantidad(detalle.getCantidad());
                    return articuloStockDTO;
                })
                .collect(Collectors.toList());
        Integer idProveedor = articulos
                .stream()
                .findFirst()
                .map(articulo -> articulo.getIdProveedor())
                .get();

        ajusteDTO.setArticulos(articulos);
        ajusteDTO.setIdProveedor(idProveedor);
        return ajusteDTO;
    }
    private ArticuloStockDTO mapToArticuloStockDTO(Articulo articulo, Date fecha) {
        ArticuloStockDTO articuloStockDTO = new ArticuloStockDTO();
        try{
            Double stock = this.movimientoArticuloRepository.stockPorArticuloPrevio(articulo, fecha);
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

    public AjusteDTO saveSetting(AjusteDTO ajusteDTO) {
        Ajuste ajusteToSave = new Ajuste();

        ajusteToSave = mappedSetting(ajusteToSave, ajusteDTO);
        ajusteToSave = ajusteRepository.save(ajusteToSave);

        if (ajusteToSave == null)
            throw new NotFoundException("\nWARNING: No se guardo el ajuste");
        mappedDetailSetting(ajusteToSave, ajusteDTO);
        return ajusteDTO;
    }

    private Ajuste mappedSetting(Ajuste ajusteTosave, AjusteDTO ajusteDTO) {
        if (validationNullSetting(ajusteDTO)) {
            throw new BadRequestException("\nError: No se pueden cargar pedidos con nombres o fechas null");
        }
        ajusteTosave.setNombre(ajusteDTO.getNombre());
        ajusteTosave.setDescripcion(ajusteDTO.getDescripcion());
        ajusteTosave.setFecha(ajusteDTO.getFecha());

        return ajusteTosave;
    }

    private boolean validationNullSetting(AjusteDTO ajusteDTO) {
        if (ajusteDTO.getNombre() == null || ajusteDTO.getFecha() == null)
            return true;
        return false;
    }

    private void mappedDetailSetting(Ajuste ajuste, AjusteDTO ajusteDTO) {
        Articulo article;
        for (ArticuloStockDTO articulo : ajusteDTO.getArticulos()) {

            MovimientoArticulo movimientoArticulo;
            DetalleAjuste detalleAjuste = new DetalleAjuste();
            article = articuloRepository.findArticuloByCodigo(articulo.getCodigoArt());
            if (article == null) {
                throw new NotFoundException("\nWARINNG: No existe articulo en base de datos");
            }
            detalleAjuste.setArticuloByIdArticulo(article);
            detalleAjuste.setAjusteByIdAjuste(ajuste);
            detalleAjuste.setFecha(ajusteDTO.getFecha());
            detalleAjuste.setCantidad(articulo.getCantidad());
            detalleAjuste = detalleAjusteRepository.save(detalleAjuste);
            if (detalleAjuste == null) {
                throw new NotFoundException("\nWARNING: Error al almacenar el detalle del pedido");
            }
            movimientoArticulo = this.movimientoArticuloService.saveMovimientoSetting(detalleAjuste);
            if (movimientoArticulo == null) {
                throw new NotFoundException("\nWARNING: Error en la carga de movimientos");
            }
        }
    }

}
