package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.TipoCheque;
import com.undec.corralon.repository.TipoChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoChequeService {
    @Autowired
    TipoChequeRepository tipoChequeRepository;

    public List<TipoCheque> listAllTypeCheck() {
        List<TipoCheque> TipoCheque = this.tipoChequeRepository.findAll();
        if (TipoCheque == null) throw new NotFoundException("\nWARNING: Error no exiten Tipos Cheque");
        return TipoCheque;
    }

    public List<TipoCheque> listAllTypeCheckEnabled() {
        List<TipoCheque> settingEnabled = this.tipoChequeRepository.findTipoChequeByHabilitadoEquals(true);
        if (settingEnabled == null) throw new NotFoundException("\nWARNING: Error no existen ajustes habilitados");
        return settingEnabled;
    }

    public TipoCheque findTypeCheckById(Integer id) {
        TipoCheque checkSelected = this.tipoChequeRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: Error no existe tipo cheque por id"));
        return checkSelected;
    }

    public TipoCheque saveTypeCheck(TipoCheque receivedTypeCheck) {
        TipoCheque checkToSave = new TipoCheque();

        checkToSave.setHabilitado(true);
        checkToSave.setNombre(receivedTypeCheck.getNombre());
        checkToSave.setDescripcion(receivedTypeCheck.getDescripcion());

        tipoChequeRepository.save(checkToSave);

        if (checkToSave == null)
            throw new NotFoundException("\nWARNING: No se guardo el ajuste");

        return checkToSave;
    }

    public TipoCheque modifyTypeCheck(TipoCheque typeCheck) {
        if (typeCheck.getIdTipoCheque() == null || typeCheck.getNombre() == null) {
            throw new BadRequestException("\nError no se puede enviar un tipo de cheque con nombre o id vacio");
        }
        TipoCheque typeCheckModify = tipoChequeRepository.findById(typeCheck.getIdTipoCheque()).
                orElseThrow(() -> new NotFoundException("\nWARNING: no existe el tipo de cheque, o el id es incorrecto"));
        typeCheckModify.setNombre(typeCheck.getNombre());
        typeCheckModify.setDescripcion(typeCheck.getDescripcion());
        typeCheckModify = tipoChequeRepository.save(typeCheckModify);

        if (typeCheckModify == null) {
            throw new NotFoundException("\nError al guardar los cambios de tipo cheque");
        }
        return typeCheckModify;
    }

    public TipoCheque changeEnablementToTypeCheck(Integer idTypeCheck) {
        if (idTypeCheck == null) {
            throw new BadRequestException("\nWARNING: El identificador de tipo de cheque es null");
        }
        TipoCheque typeCheck = tipoChequeRepository.findById(idTypeCheck).
                orElseThrow(
                        () -> new NotFoundException("WARNIGN: no existe el tipo de cheque"));

        typeCheck.setHabilitado(!typeCheck.getHabilitado());
        typeCheck = tipoChequeRepository.save(typeCheck);
        if (typeCheck == null) {
            throw new NotFoundException("\nWARNING: error al realizar el cambio de habilitacion del tipo de cheque");
        }
        return typeCheck;
    }
}

