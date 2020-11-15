package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.unidadMedida.*;
import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaService {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public Response obtenerTodasLasUnidadesDeMedida() {

        List<UnidadMedida> unidadDeMedida = this.unidadMedidaRepository.findAll();
        Response response = new Response();

        response.setCode(200);
        response.setData(unidadDeMedida);
        response.setMsg("Unidades de Medida");

        return response;
    }

    public Response obtenerUnidadMedidaHabilitados() {
        List<UnidadMedida> unidadDeMedida = this.unidadMedidaRepository.findAllByHabilitacionEquals(true);
        Response response = new Response();

        response.setCode(200);
        response.setData(unidadDeMedida);
        response.setMsg("Unidades de Medidas habilitadas");

        return response;
    }

    public Response obtenerUnidadMedidaPorId(Integer id) {

        UnidadMedida unidadDeMedida = this.unidadMedidaRepository.findById(id).get();
        Response response = new Response();

        response.setCode(200);
        response.setData(unidadDeMedida);
        response.setMsg("Unidad de Medida");

        return response;
    }

    public Response crearUnidadMedida(UnidadMedida unidadMedida) throws UnidadMedidaException {

        Response response = new Response();
        unidadMedida.setHabilitacion(true);
        unidadMedida.setFechaCreacion(new Date());
        UnidadMedida unidadMedidaToSave = this.unidadMedidaRepository.save(unidadMedida);

        if (unidadMedidaToSave == null)
            throw new UnidadMedidaErrorToSave();

        response.setMsg("Unidad medida guardada correctamente");
        response.setCode(200);
        response.setData(unidadMedidaToSave);

        return response;
    }

    public Response actualizarUnidadMedida(UnidadMedida unidadMedida) throws UnidadMedidaException {

        Response response = new Response();
        UnidadMedida unidadMedidaToUpdate = this.unidadMedidaRepository.findById(unidadMedida.getId()).get();

        if (unidadMedidaToUpdate == null)
            throw new UnidadMedidaErrorToUpdate();

        unidadMedidaToUpdate.setNombre(unidadMedida.getNombre());
        unidadMedidaToUpdate.setAbreviatura(unidadMedida.getAbreviatura());
        unidadMedidaToUpdate.setFechaModificacion(new Date());

        unidadMedidaToUpdate = this.unidadMedidaRepository.save(unidadMedidaToUpdate);

        response.setMsg("Unidad medida actualizada correctamente");
        response.setCode(200);
        response.setData(unidadMedidaToUpdate);

        return response;
    }

    public Response cambiarHabilitacion(Integer id) throws UnidadMedidadCambioHabilitacionExceptioon {
        Response response = new Response();

        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(id);
        if (!unidadMedidaOptional.isPresent()) {
            throw new UnidadMedidadCambioHabilitacionExceptioon();
        }
        UnidadMedida unidadMedida = unidadMedidaOptional.get();
        unidadMedida.setHabilitacion(!unidadMedida.getHabilitacion());
        unidadMedida = unidadMedidaRepository.save(unidadMedida);

        response.setCode(200);
        response.setMsg("Unidad de Medida cambio el estado");
        response.setData(unidadMedida);
        return response;
    }

}
