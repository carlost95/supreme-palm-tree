package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.banco.*;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BancoService {
    @Autowired
    BancoRepository bancoRepository;

    public Response listarTodos() throws EntityNotFoundException {
        Response response = new Response();
        List<Banco> bancos = bancoRepository.findAll();

        if (bancos == null)
            throw new EntityNotFoundException();

        response.setCode(200);
        response.setMsg("Bancos listados correctamente");
        response.setData(bancos);

        return response;
    }

    public Response listarTodosHabilitados() throws EntityNotFoundException {
        Response response = new Response();
        List<Banco> bancos = bancoRepository.findAllByHabilitadoEquals((byte) 1);
        if (bancos == null)
            throw new EntityNotFoundException();
        response.setCode(200);
        response.setMsg("Bancos habilitados listados correctamente");
        response.setData(bancos);

        return response;
    }

    public Response obtenerBancoPorId(Integer id) throws BancoNotFoundException {

        Response response = new Response();
        Optional<Banco> bancoOptional = bancoRepository.findById(id);

        if (!bancoOptional.isPresent()) {
            throw new BancoNotFoundException();
        }

        response.setCode(200);
        response.setMsg("Banco solicitado");
        response.setData(bancoOptional.get());
        return response;
    }

    public Response guardarBanco(Banco banco) throws BancoErrorToSaveException {
        Response response = new Response();
        banco.setHabilitado(true);
        Banco bancoToSave = bancoRepository.save(banco);

        if (bancoToSave == null)
            throw new BancoErrorToSaveException();
        response.setCode(200);
        response.setMsg("banco guardado exitosamente");
        response.setData(bancoToSave);
        return response;
    }

    public Response actualizarBanco(Banco banco) throws BancoErrorToUpdateException {
        Response response = new Response();
        Banco bancoToUpdate = bancoRepository.findById(banco.getId()).get();

        if (bancoToUpdate == null) {
            throw new BancoErrorToUpdateException();
        }

        bancoToUpdate.setNombre(banco.getNombre());
        bancoToUpdate.setAbreviatura(banco.getAbreviatura());

        response.setCode(200);
        response.setMsg("Banco actualizado con exito");
        response.setData(bancoRepository.save(bancoToUpdate));
        return response;
    }

    public Response cambiarHabilitacion(Integer id) throws BancoCambioEstadoException {
        Response response = new Response();

        Optional<Banco> bancoOptional = bancoRepository.findById(id);
        if (!bancoOptional.isPresent()){
            throw new BancoCambioEstadoException();
        }
        Banco banco = bancoOptional.get();
        banco.setHabilitado(!banco.getHabilitado());
        banco = bancoRepository.save(banco);

        response.setCode(200);
        response.setMsg("El banco cambio el estado");
        response.setData(banco);
        return response;
    }

}
