package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.TipoCheque;
import com.undec.corralon.service.TipoChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/tipo-cheque")

public class TipoChequeController {
    @Autowired
    TipoChequeService tipoChequeService;

    @GetMapping
    public ResponseEntity<List<TipoCheque>> listAllTypeCheck() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoChequeService.listAllTypeCheck());
    }

    @GetMapping("/habilitado")
    public ResponseEntity<List<TipoCheque>> listAllTypeCheckEnabled() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoChequeService.listAllTypeCheckEnabled());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCheque> findCheckById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoChequeService.findTypeCheckById(id));
    }

    @PostMapping
    public ResponseEntity<TipoCheque> saveCheck(@RequestBody TipoCheque typeCheck) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoChequeService.saveTypeCheck(typeCheck));
    }

    @PutMapping("/id")
    public  ResponseEntity<TipoCheque> changeEnablementToTypeCheck(Integer id){
        return  ResponseEntity.status(HttpStatus.OK).body(tipoChequeService.changeEnablementToTypeCheck(id));
    }

    @PutMapping
    public ResponseEntity <TipoCheque> modifyTypeCheck(TipoCheque typeCheck){
        return ResponseEntity.status(HttpStatus.OK).body(tipoChequeService.modifyTypeCheck(typeCheck));
    }

}
