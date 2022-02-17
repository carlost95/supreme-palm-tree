package com.undec.corralon.controlador;


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

    @GetMapping("/habilitado")
    public ResponseEntity<List<Cheque>> listAllCheckEnabled() {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.listAllCheckEnabled());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cheque> findCheckById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(chequeService.findCheckById(id));
    }
}
