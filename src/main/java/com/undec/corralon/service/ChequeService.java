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
        return chequeToSave;
    }

    private TipoCheque validarTypeCheck(Integer idTipoCheque) {
        TipoCheque typeCheck = new TipoCheque();
        if (typeCheck != null) {
            typeCheck = tipoChequeRepository.findById(idTipoCheque).
                    orElseThrow(
                            () -> new NotFoundException("\nError: No existe un typo cheque con ese id para cargar al cheque"));
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
