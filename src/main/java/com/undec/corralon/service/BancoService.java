package com.undec.corralon.service;

import com.undec.corralon.DTO.BancoRequest;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService {
    @Autowired
    BancoRepository bancoRepository;

    public Response listOfBank() {
        Response response = new Response();
        List<Banco> bancos = bancoRepository.findAll();

        if (bancos == null)
            throw new NotFoundException("WARNING: No existen bancos");

        response.setCode(200);
        response.setMsg("Bancos listados correctamente");
        response.setData(bancos);

        return response;
    }

    public Response listOfBankHalilitation() {
        Response response = new Response();
        List<Banco> bancos = bancoRepository.findAllByHabilitadoEquals(true);
        if (bancos == null)
            throw new NotFoundException("WARNING: No existen bancos habilitados");
        response.setCode(200);
        response.setMsg("lista de bancos habilitados");
        response.setData(bancos);

        return response;
    }

    public Response listOfBankForId(Integer id) {
        Response response = new Response();
        Banco bancoOptional = bancoRepository.findById(id).
                orElseThrow(()
                        -> new NotFoundException("WARNING: No existe el banco por id requerido"));

        response.setCode(200);
        response.setMsg("Banco por id");
        response.setData(bancoOptional);
        return response;
    }

    public Response saveOfBank(BancoRequest bancoRequest) {
        Response response = new Response();
        Banco bank = new Banco();
        bank.setNombre(bancoRequest.getNombre());
        bank.setAbreviatura(bancoRequest.getAbreviatuira());
        bank.setHabilitado(true);

        Banco bancoToSave = bancoRepository.save(bank);

        if (bancoToSave == null)
            throw new NotFoundException("WARNING: No se puede guardar banco");

        response.setCode(200);
        response.setMsg("banco guardado exitosamente");
        response.setData(bancoToSave);
        return response;
    }

    public Banco updatedBank(Banco banco) {
        Response response = new Response();

        if (banco.getIdBanco() == null) throw new BadRequestException("WARNING: No se cargaron los datos del banco");

        Banco bancoToUpdate = bancoRepository.findById(banco.getIdBanco()).
                orElseThrow(() -> new NotFoundException("WARNING: No existe el banco que se quiere actualizar"));

        Banco bancoMapped = mappedBanco(bancoToUpdate);
        bancoToUpdate = bancoRepository.save(bancoMapped);
        if (bancoToUpdate == null) throw new NotFoundException("WARNING: No se guardo el banco a actualizar");

//        response.setCode(200);
//        response.setMsg("Banco actualizado con exito");
//        response.setData(bancoToUpdate);
//        return response;
        return bancoToUpdate;
    }


    public Response changeOfHabilitationBank(Integer id) {
        Response response = new Response();
        Banco bancoOptional = bancoRepository.findById(id)
                .orElseThrow(()
                        -> new NotFoundException("WARNING: No existe el banco que se quiere cambiar el estado de habilitacion"));

        Banco banco = bancoOptional;
        banco.setHabilitado(!bancoOptional.getHabilitado());
        banco = bancoRepository.save(banco);

        response.setCode(200);
        response.setMsg("El banco cambio el estado de habilitacion");
        response.setData(banco);
        return response;
    }

    private Banco mappedBanco(Banco banco) {
        Banco bancoMapped = new Banco();

        bancoMapped.setIdBanco(banco.getIdBanco());
        bancoMapped.setNombre(banco.getNombre());
        bancoMapped.setAbreviatura(banco.getAbreviatura());
        bancoMapped.setHabilitado(banco.getHabilitado());
        return bancoMapped;
    }


}
