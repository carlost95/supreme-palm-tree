package com.undec.corralon.service;

import com.undec.corralon.DTO.AjusteDTO;
import com.undec.corralon.DTO.DetalleTipoMovimientoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.Util;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToSaveException;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToUpdateException;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToUpdateHabilitacion;
import com.undec.corralon.excepciones.Ajuste.AjusteException;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Ajuste;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.DetalleAjuste;
import com.undec.corralon.modelo.MovimientoArticulo;
import com.undec.corralon.repository.AjusteRepository;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.DetalleAjusteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    public List<Ajuste> findAllTheSetting() {
        List<Ajuste> ajustes = this.ajusteRepository.findAll();
        if (ajustes == null) throw new NotFoundException("\nWARNING: Error no exiten ajustes");
        return ajustes;
    }

    public List<Ajuste> findAllSettingEnabled() {
        List<Ajuste> settingEnabled = this.ajusteRepository.findAjusteByHabilitadoEquals(true);
        if (settingEnabled == null) throw new NotFoundException("\nWARNING: Error no existen ajustes habilitados");
        return settingEnabled;
    }

    public Ajuste findSettingById(Integer id) {
        Ajuste settingSelect = this.ajusteRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: Error no existe ajuste por id"));
        return settingSelect;
    }

    public AjusteDTO saveSetting(AjusteDTO ajusteDTO) {
        Ajuste ajusteToSave = new Ajuste();

        ajusteToSave.setHabilitado(true);
        ajusteToSave = mappedSetting(ajusteToSave, ajusteDTO);
        ajusteToSave = ajusteRepository.save(ajusteToSave);

        if (ajusteToSave == null)
            throw new NotFoundException("\nWARNING: No se guardo el ajuste");
        mappedDetailSetting(ajusteToSave, ajusteDTO);

        return ajusteDTO;
    }

    public Ajuste modifySettingh(Ajuste ajuste) {
        if (validationSettingNull(ajuste))
            throw new BadRequestException("\nError: no se admiten valores nullos en los ajustes a modificar");

        Ajuste ajusteModify = this.ajusteRepository.findById(ajuste.getIdAjuste())
                .orElseThrow(() ->
                        new NotFoundException("\nWARING: no existe un ajuste por modificar"));

        ajusteModify.setNombre(ajuste.getNombre());
        ajusteModify.setDescripcion(ajuste.getDescripcion());
        ajusteModify.setFecha(ajuste.getFecha());

        ajusteModify = this.ajusteRepository.save(ajusteModify);
        if (ajusteModify == null)
            throw new NotFoundException("\nError al almacenar ajuste no se puede modificar");

        return ajusteModify;
    }

    private boolean validationSettingNull(Ajuste ajuste) {
        if (ajuste.getIdAjuste() == null
                || ajuste.getNombre() == null
                || ajuste.getFecha() == null) {
            return true;
        }
        return false;
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

        for (DetalleTipoMovimientoDTO detalle : ajusteDTO.getDetallesAjuste()) {
            MovimientoArticulo movimientoArticulo;
            DetalleAjuste detalleAjuste = new DetalleAjuste();
            article = articuloRepository.findArticuloForCodigo(detalle.getNombreArticulo(), detalle.getCodigoArticulo());
            if (article == null) {
                throw new NotFoundException("\nWARINNG: No existe articulo en base de datos");
            }
            detalleAjuste.setArticuloByIdArticulo(article);
            detalleAjuste.setAjusteByIdAjuste(ajuste);
            detalleAjuste.setFecha(ajusteDTO.getFecha());
            detalleAjuste.setCantidad(detalle.getValorIngresado());
            detalleAjuste = detalleAjusteRepository.save(detalleAjuste);

            if (detalleAjuste == null) {
                throw new NotFoundException("\nWARNING: Error al almacenar el detalle del ajuste");
            }
            movimientoArticulo = this.movimientoArticuloService.saveMovimientoSetting(detalleAjuste);
            if (movimientoArticulo == null)
                throw new NotFoundException("\nWARNING: Error en la carga de movimientos");
        }
    }


    public Ajuste changeHabilitationSetting(Integer id) {
        if (id == null) {
            throw new BadRequestException("\nWARNING: El identificador de ajuste es null");
        }
        Ajuste ajusteOptional = ajusteRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("WARNIGN: no existe el ajuste"));

        ajusteOptional.setHabilitado(!ajusteOptional.getHabilitado());
        ajusteOptional = ajusteRepository.save(ajusteOptional);
        if (ajusteOptional == null) {
            throw new NotFoundException("\nWARNING: error al realizar el cambio de habilitacion del ajuste");
        }
        return ajusteOptional;
    }
}
