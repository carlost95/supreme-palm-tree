package com.undec.corralon.controlador;


import com.undec.corralon.modelo.Empresa;
import com.undec.corralon.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/empresas")

public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> findAllEmpresas(){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findAllEmpresas());
    }
    @GetMapping ("/habilitadas")
    public ResponseEntity< List<Empresa>> findAllEmpresaEnabled(){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findAllByStatusIsTrue());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findEmpresaById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findEmpresaById(id));
    }
    @PostMapping
    public ResponseEntity<Empresa> saveEmpresa(@RequestBody Empresa empresa){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.saveEmpresa(empresa));
    }
    @PutMapping
    public ResponseEntity<Empresa> updateEmpresa(@RequestBody Empresa empresa){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.updateEmpresa(empresa));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> habilitationChange(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.changeStatusEmpresa(id));
    }
}
