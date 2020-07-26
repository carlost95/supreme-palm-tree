package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.modelo.Tipodireccion;
import com.undec.corralon.service.TipoDireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/tipo-direccion")

public class TipoDireccionController {
    @Autowired
    TipoDireccionService tipoDireccionService;

    @GetMapping
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = tipoDireccionService.listarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/habilitados")
    public ResponseEntity<Response> listarHabilitados() throws Exception {
        Response response = tipoDireccionService.listarTodosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Response response = tipoDireccionService.listarPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Response> guardar(@Valid @RequestBody Tipodireccion tipodireccion) throws Exception {
        Response response = tipoDireccionService.guardar(tipodireccion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@Valid @RequestBody Tipodireccion tipodireccion) throws Exception {
        Response response = tipoDireccionService.actualizar(tipodireccion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> eliminar(@PathVariable("id") Integer id) throws Exception {
        Response response = tipoDireccionService.darDeBaja(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
