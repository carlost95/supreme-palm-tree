package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService {
    @Autowired
    BancoRepository bancoRepository;

    public List<Banco> listOfBank() {
        List<Banco> bancos = bancoRepository.findAll();

        if (bancos == null)
            throw new NotFoundException("\nWARNING: No existen bancos");

        return bancos;
    }

    public List<Banco> listOfBankHalilitation() {
        List<Banco> bancos = bancoRepository.findAllByHabilitadoEquals(true);

        if (bancos == null)
            throw new NotFoundException("\nWARNING: No existen bancos habilitados");

        return bancos;
    }

    public Banco listOfBankForId(Integer id) {
        Banco bancoOptional = bancoRepository.findById(id).
                orElseThrow(()
                        -> new NotFoundException("\nWARNING: No existe el banco por id requerido"));

        return bancoOptional;
    }

    public Banco saveOfBank(Banco banco) {
        Banco bank = new Banco();

        bank.setNombre(banco.getNombre());
        bank.setAbreviatura(banco.getAbreviatura());
        bank.setHabilitado(true);

        Banco bancoToSave = bancoRepository.save(bank);

        if (bancoToSave == null)
            throw new NotFoundException("\nWARNING: No se puede guardar banco");

        return bancoToSave;
    }

    public Banco updatedBank(Banco banco) {
        if (banco.getIdBanco() == null) throw new BadRequestException("\nWARNING: No se cargaron los datos del banco");

        Banco bancoToUpdate = bancoRepository.findById(banco.getIdBanco()).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe el banco que se quiere actualizar"));

        bancoToUpdate.setIdBanco(banco.getIdBanco());
        bancoToUpdate.setNombre(banco.getNombre());
        bancoToUpdate.setAbreviatura(banco.getAbreviatura());
        bancoToUpdate.setHabilitado(banco.getHabilitado());

        bancoToUpdate = bancoRepository.save(bancoToUpdate);
        if (bancoToUpdate == null) throw new NotFoundException("\nWARNING: No se guardo el banco a actualizar");

        return bancoToUpdate;
    }


    public Banco changeOfHabilitationBank(Integer id) {
        Banco bancoOptional = bancoRepository.findById(id)
                .orElseThrow(()
                        -> new NotFoundException("\nWARNING: No existe el banco que se quiere cambiar el estado de habilitacion"));

        Banco banco = bancoOptional;
        banco.setHabilitado(!bancoOptional.getHabilitado());
        banco = bancoRepository.save(banco);
        if (banco == null)
            throw new NotFoundException("\nWARNING: Error al almacenar los cambios");
        return banco;
    }

}
