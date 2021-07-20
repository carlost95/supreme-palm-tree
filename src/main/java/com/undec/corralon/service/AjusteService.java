package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToSaveException;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToUpdateException;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToUpdateHabilitacion;
import com.undec.corralon.excepciones.Ajuste.AjusteException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Ajuste;
import com.undec.corralon.repository.AjusteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AjusteService {
    @Autowired
    AjusteRepository ajusteRepository;

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
                        () -> new NotFoundException("\nWARNIG: Error no exuste ajuste opor id"));
        return settingSelect;
    }

    public Ajuste saveAjuste(Ajuste ajuste) {
        Ajuste ajusteToSave = new Ajuste();
        ajuste.setHabilitado(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaDeCarga = LocalDateTime.now().format(formatter).toString();

        horaDeCarga = horaDeCarga.substring(10, horaDeCarga.length());
        ajuste.setFecha(ajuste.getFecha() + horaDeCarga);
        ajusteToSave = this.ajusteRepository.save(ajuste);

        return ajusteToSave;
    }

    public Response modificarAjuste(Ajuste ajuste) throws AjusteException {
        Response response = new Response();
        Ajuste ajusteToSave = this.ajusteRepository.findById(ajuste.getIdAjuste()).get();

        ajusteToSave.setNombre(ajuste.getNombre());
        ajusteToSave.setDescripcion(ajuste.getDescripcion());
        ajusteToSave.setFecha(ajuste.getFecha());

        if (ajusteToSave == null)
            throw new AjusteErrorToUpdateException("error al actualizar el ajuste ");

        this.ajusteRepository.save(ajusteToSave);
        response.setCode(200);
        response.setData(ajusteToSave);
        response.setMsg("Pedido actualizado");

        return response;

    }

    public Response cambiarHabilitacionAjuste(Integer id) throws AjusteException {
        Response response = new Response();
        Ajuste ajusteOptional = ajusteRepository.findById(id).get();

        if (ajusteOptional == null) {
            throw new AjusteErrorToUpdateHabilitacion("error the update habilitacion");
        }

        ajusteOptional.setHabilitado(!ajusteOptional.getHabilitado());
        ajusteOptional = ajusteRepository.save(ajusteOptional);
        response.setCode(200);
        response.setMsg("se cambio habilitacion de ajuste");
        response.setData(ajusteOptional);

        return response;
    }

}
