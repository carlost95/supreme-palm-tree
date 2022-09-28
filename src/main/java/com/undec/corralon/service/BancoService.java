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
        if (this.bancoRepository.existsBancoByNombreOrAbreviatura(banco.getNombre(), banco.getAbreviatura())) {
            throw new BadRequestException("Ya existe registrado un banco con el mismo nombre o abreviatura");
        }

        bank.setNombre(banco.getNombre());
        bank.setAbreviatura(banco.getAbreviatura());
        bank.setHabilitado(true);

        bank = bancoRepository.save(bank);

        if (bank.toString().isEmpty())
            throw new NotFoundException("\nWARNING: No se puede guardar banco");

        return bank;
    }

    public Banco updatedBank(Banco banco) {
        Banco bancoToUpdate = bancoRepository.findById(banco.getIdBanco()).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe el banco que se quiere actualizar"));

        bancoToUpdate.setIdBanco(banco.getIdBanco());
        bancoToUpdate.setNombre(banco.getNombre());
        bancoToUpdate.setAbreviatura(banco.getAbreviatura());
        bancoToUpdate.setHabilitado(banco.getHabilitado());
        validateUpdataBank(bancoToUpdate);
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
        if (banco.toString().isEmpty())
            throw new NotFoundException("\nWARNING: Error al almacenar los cambios");
        return banco;
    }

    private void validateUpdataBank(Banco banco) {
        if (banco.getNombre().isEmpty())
            throw new BadRequestException("El nombre del banco no puede estar vacio o tener mas de 50 caracteres");
        if (banco.getAbreviatura().isEmpty())
            throw new BadRequestException("La abreviatura del banco no puede estar vacio o tener mas de 10 caracteres");
        if (bancoRepository.existsBancoByNombreAndIdBancoNot(banco.getNombre(), banco.getIdBanco()))
            throw new BadRequestException("Ya existe un banco con el mismo nombre");
        if (bancoRepository.existsBancoByAbreviaturaAndIdBancoNot(banco.getAbreviatura(), banco.getIdBanco()))
            throw new BadRequestException("Ya existe un banco con la misma abreviatura");
    }

}
