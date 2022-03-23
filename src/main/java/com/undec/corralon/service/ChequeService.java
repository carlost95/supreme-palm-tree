package com.undec.corralon.service;

import com.undec.corralon.DTO.ChequeDTO;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.modelo.Cheque;
import com.undec.corralon.modelo.TipoCheque;
import com.undec.corralon.repository.BancoRepository;
import com.undec.corralon.repository.ChequeRepository;
import com.undec.corralon.repository.TipoChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeService {
    @Autowired
    ChequeRepository chequeRepository;

    @Autowired
    BancoRepository bancoRepository;

    @Autowired
    TipoChequeRepository tipoChequeRepository;

    public List<Cheque> listAllCheck() {
        List<Cheque> checks = chequeRepository.findAll();
        if (checks == null) {
            throw new NotFoundException("\nWarnig: no exiten queques");
        }
        return checks;
    }

    public List<Cheque> listAllCheckEnabled() {
        List<Cheque> checksEnableds = chequeRepository.findChequeByHabilitadoEquals(true);
        if (checksEnableds == null) {
            throw new NotFoundException("\nWarning: No existen cheques habilitados");
        }
        return checksEnableds;
    }

    public Cheque findCheckById(Integer id) {
        Cheque check = chequeRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWarning: No existe el cheque por ID"));
        return check;
    }

    public Cheque saveCheck(ChequeDTO checkDto) {
        Cheque checkToSave = new Cheque();
        return mappedCheckToSave(checkDto, checkToSave);
    }

    public Cheque changeHabilityToCheck(Integer idCheck) {
        Cheque checkChangeHability = chequeRepository.findById(idCheck).
                orElseThrow(() -> new NotFoundException("Error: No exite cheque en la base de datos"));
        checkChangeHability.setHabilitado(!checkChangeHability.getHabilitado());
        checkChangeHability = chequeRepository.save(checkChangeHability);
        if (checkChangeHability == null) {
            throw new NotFoundException("Error al cambiar el estado de activo del cheque");
        }
        return checkChangeHability;
    }

    public Cheque modifyCheck(ChequeDTO chequeDTO) {
        Cheque checkModify = new Cheque();
        if (validaDateCheck(chequeDTO)) {
            mappedCheckToSave(chequeDTO, checkModify);
        }
        return checkModify;
    }

    private boolean validaDateCheck(ChequeDTO chequeDTO) {
        if (chequeDTO.getFecha() != null || chequeDTO.getFechaEmision() != null || chequeDTO.getFechaVenciomiento() != null) {
            return true;
        } else
            return false;
    }

    private Cheque mappedCheckToSave(ChequeDTO checkDto, Cheque chequeToSave) {
        Banco bank = validarBank(checkDto.getIdBanco());
        TipoCheque typeCheck = validarTypeCheck(checkDto.getIdTipoCheque());

        chequeToSave.setBancoByIdBanco(bank);
        chequeToSave.setTipoChequeByIdTipoCheque(typeCheck);

        chequeToSave.setHabilitado(true);
        chequeToSave.setFecha(checkDto.getFecha());
        chequeToSave.setFechaEmision(checkDto.getFechaEmision());
        chequeToSave.setFechaCobro(checkDto.getFechaCobro());
        chequeToSave.setFechaVenciomiento(checkDto.getFechaVenciomiento());
        chequeToSave.setTitularEmisor(checkDto.getTitularEmisor());
        chequeToSave = chequeRepository.save(chequeToSave);
        if (checkDto == null) {
            throw new NotFoundException("\nError guardar datos de  cheque");
        }
        return chequeToSave;
    }

    private TipoCheque validarTypeCheck(Integer idTipoCheque) {
        TipoCheque typeCheck = new TipoCheque();
        if (typeCheck != null) {
            typeCheck = tipoChequeRepository.findById(idTipoCheque).
                    orElseThrow(
                            () -> new NotFoundException("\nError: No existe un tipo cheque con ese id para cargar al cheque"));
        } else {
            throw new BadRequestException("\nError: el id del tipo cheque es null");
        }
        return typeCheck;
    }

    private Banco validarBank(Integer idBank) {
        Banco bankData = new Banco();
        if (idBank != null) {
            bankData = bancoRepository.findById(idBank).
                    orElseThrow(
                            () -> new NotFoundException("\nError: No existe un banco con ese id para cargar al cheque"));
        } else {
            throw new BadRequestException("\nError: el id del banco es null");
        }
        return bankData;
    }
}
