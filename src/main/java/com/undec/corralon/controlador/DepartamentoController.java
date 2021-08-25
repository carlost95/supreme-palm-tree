package com.undec.corralon.controlador;

import com.undec.corralon.DTO.DepartamentoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<Departamento>> listAllDepartment() {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.listAllDepartment());
    }

    @GetMapping("/habilitado")
    public ResponseEntity<List<Departamento>> listarHabilitados() {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.listAllDepartmentHabilitation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> listarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findByIdDepartment(id));
    }

    @PostMapping
    public ResponseEntity<Departamento> saveDepartment(@RequestBody Departamento departamento) {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.saveDepartment(departamento));
    }

    @PutMapping
    public ResponseEntity<Departamento> updatedDepartment(@RequestBody Departamento departamento){
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.updatedDepartment(departamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> changeStatus(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.changeStatus(id));
    }
}
