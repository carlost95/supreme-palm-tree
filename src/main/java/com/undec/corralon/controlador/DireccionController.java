package com.undec.corralon.controlador;


import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.DireccionErrorToSaveException;
import com.undec.corralon.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    @Autowired
    DireccionService direccionService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> buscarPorCliente(@PathVariable("id") Integer idCliente) throws DireccionErrorToSaveException {
        Response response = direccionService.buscarDireccionPorCliente(idCliente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardar(@Valid @RequestBody DireccionDTO direccionDTO) throws DireccionErrorToSaveException {
        Response response = direccionService.guardarDireccion(direccionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
