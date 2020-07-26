package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.formaDePago.FormaDePagoErrorToSaveException;
import com.undec.corralon.excepciones.formaDePago.FormaDePagoErrorToUpdateException;
import com.undec.corralon.modelo.FormaPago;
import com.undec.corralon.repository.FormaDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FormaDePagoService {

    @Autowired
    FormaDePagoRepository formaDePagoRepository;

    public Response obtenerTodasLasFormasDePago(){
        Response response = new Response();
        List<FormaPago> formasDePago = this.formaDePagoRepository.findAll();

        response.setCode(200);
        response.setMsg("Formas de pago");
        response.setData(formasDePago);

        return response;
    }

    public Response obtenerFormasDePagoPorId(Integer id){
        Response response = new Response();
        FormaPago formaDePago = this.formaDePagoRepository.findById(id).get();

        response.setCode(200);
        response.setMsg("Formas de pago");
        response.setData(formaDePago);

        return response;
    }

    public Response obtenerHabilitados(){
        Response response = new Response();
        List<FormaPago> formasDePago = this.formaDePagoRepository.findByHabilitacionEquals(1);

        response.setCode(200);
        response.setMsg("Formas de pago habilitadas");
        response.setData(formasDePago);

        return response;
    }

    public Response agregarFormaDePago(FormaPago formaPago) throws Exception{

        Response response = new Response();
        formaPago.setFechaCreacion(new Date());
        formaPago.setHabilitacion(1);

        formaPago = this.formaDePagoRepository.save(formaPago);

        if(formaPago == null){
            throw new FormaDePagoErrorToSaveException();
        }

        response.setMsg("Guardado");
        response.setCode(200);
        response.setData(formaPago);

        return response;
    }

    public Response actualizarFormaDePago(FormaPago formaPago) throws Exception {

        Response response = new Response();
        FormaPago formaPagoToUpdate = this.formaDePagoRepository.findById(formaPago.getId()).get();
        if(formaPago == null){
            throw new FormaDePagoErrorToUpdateException();
        }

        formaPagoToUpdate.setId(formaPago.getId());
        formaPagoToUpdate.setNombre(formaPago.getNombre());
        formaPagoToUpdate.setDescripcion(formaPago.getDescripcion());
        formaPagoToUpdate.setAumento(formaPago.getAumento());
        formaPagoToUpdate.setHabilitacion(formaPago.getHabilitacion());
        formaPagoToUpdate.setFechaModificacion(new Date());

        formaPagoToUpdate = this.formaDePagoRepository.save(formaPagoToUpdate);

        response.setMsg("Guardado");
        response.setCode(200);
        response.setData(formaPagoToUpdate);

        return response;
    }

    public Response darDeBaja(Integer id) throws Exception{
        Response response = new Response();
        FormaPago formaPago = formaDePagoRepository.findById(id).get();

        if(formaPago == null)
            throw new FormaDePagoErrorToUpdateException();

        formaPago.setHabilitacion(0);
        formaPago.setFechaBaja(new Date());
        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(formaDePagoRepository.save(formaPago));

        return response;
    }


}
