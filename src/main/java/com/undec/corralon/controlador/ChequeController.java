package com.undec.corralon.controlador;


import com.undec.corralon.DTO.ChequeDTO;
import com.undec.corralon.modelo.Cheque;
import com.undec.corralon.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cheques")
public class ChequeController {
    @Autowired
    ChequeService chequeService;

    @GetMapping
    public ResponseEntity<List<Cheque>> listAllCheck() {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.listAllCheck());
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Cheque>> listAllCheckEnabled() {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.listAllCheckEnabled());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cheque> findCheckById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.findCheckById(id));
    }

    @PostMapping
    public ResponseEntity<Cheque> saveCheck(@RequestBody ChequeDTO checkDto) {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.saveCheck(checkDto));
    }

    @PutMapping
    public ResponseEntity<Cheque> modifyDataCheck(@RequestBody ChequeDTO chequeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.modifyCheck(chequeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cheque> changeHabilityCheck(@PathVariable("id") Integer idCheck) {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.changeHabilityToCheck(idCheck));
    }
}
