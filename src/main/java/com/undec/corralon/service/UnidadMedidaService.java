package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.unidadMedida.UnidadMedidaErrorToDelete;
import com.undec.corralon.excepciones.unidadMedida.UnidadMedidaErrorToSave;
import com.undec.corralon.excepciones.unidadMedida.UnidadMedidaErrorToUpdate;
import com.undec.corralon.excepciones.unidadMedida.UnidadMedidaException;
import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        List<UnidadMedida> unidadDeMedida = this.unidadMedidaRepository.findAllByHabilitacionEquals(1);
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
        unidadMedida.setHabilitacion(1);
        unidadMedida.setFechaCreacion(new Date());
        UnidadMedida unidadMedidaToSave = this.unidadMedidaRepository.save(unidadMedida);

        if(unidadMedidaToSave == null)
            throw new UnidadMedidaErrorToSave();

        response.setMsg("Unidad medida Guardada");
        response.setCode(200);
        response.setData(unidadMedidaToSave);

        return response;
    }

    public Response actualizarUnidadMedida(UnidadMedida unidadMedida) throws UnidadMedidaException {

        Response response = new Response();
        UnidadMedida unidadMedidaToUpdate = this.unidadMedidaRepository.findById(unidadMedida.getId()).get();

        if(unidadMedidaToUpdate == null)
            throw new UnidadMedidaErrorToUpdate();

        unidadMedidaToUpdate.setNombre(unidadMedida.getNombre());
        unidadMedidaToUpdate.setAbreviatura(unidadMedida.getAbreviatura());
        unidadMedidaToUpdate.setFechaModificacion(new Date());
//        unidadMedidaToUpdate.setHabilitacion(unidadMedida.getHabilitacion());

        unidadMedidaToUpdate = this.unidadMedidaRepository.save(unidadMedidaToUpdate);

        response.setMsg("Unidad medida actualizada");
        response.setCode(200);
        response.setData(unidadMedidaToUpdate);

        return response;
    }


    public Response eliminarUnidadMedida(Integer id) throws UnidadMedidaException {

        Response response = new Response();
        UnidadMedida unidadMedidaToDelete = this.unidadMedidaRepository.findById(id).get();

        if(unidadMedidaToDelete == null)
            throw new UnidadMedidaErrorToDelete();

        unidadMedidaToDelete.setFechaEliminacion(new Date());
        unidadMedidaToDelete.setHabilitacion(0);

        unidadMedidaToDelete = this.unidadMedidaRepository.save(unidadMedidaToDelete);

        response.setMsg("Unidad medida deshabilitada");
        response.setCode(200);
        response.setData(unidadMedidaToDelete);

        return response;
    }


}
