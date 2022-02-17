package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Cheque;
import com.undec.corralon.repository.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeService {
    @Autowired
    ChequeRepository chequeRepository;

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
}
