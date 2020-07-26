package com.undec.corralon.controlador;

import com.undec.corralon.DTO.DistritoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin("*")
@RestController
@RequestMapping("/distritos")
public class DistritoController {

    @Autowired
    DistritoService distritoService;

    @GetMapping
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = distritoService.listarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitado")
    public ResponseEntity<Response> listarHabilitados() throws Exception {
        Response response = distritoService.listarTodosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Response response = distritoService.listarPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardar(@Valid @RequestBody DistritoDTO distritoDTO) throws Exception {
        Response response = distritoService.guardar( distritoDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@Valid @RequestBody DistritoDTO  distritoDTO) throws Exception {
        System.out.println(distritoDTO.getId() + " - " + distritoDTO.getNombre() + " - " + distritoDTO.getAbreviatura());
        Response response = distritoService.actualizar( distritoDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> eliminar(@PathVariable("id") Integer id) throws Exception {
        Response response = distritoService.darDeBaja(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
