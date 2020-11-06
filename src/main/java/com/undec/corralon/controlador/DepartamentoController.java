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

@CrossOrigin("*")
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = departamentoService.listarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> listarHabilitados() throws Exception {
        Response response = departamentoService.listarTodosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Response response = departamentoService.listarPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardar(@RequestBody DepartamentoDTO departamento) throws Exception {
        Response response = departamentoService.guardar(departamento);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@RequestBody DepartamentoDTO departamento) throws Exception {
        Response response = departamentoService.actualizar(departamento);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Response> changeStatus(@PathVariable("id") Integer id) throws Exception {
        Response response = departamentoService.changeStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
