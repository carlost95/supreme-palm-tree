package com.undec.corralon.controlador;


import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.direccion.DireccionErrorToSaveException;
import com.undec.corralon.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    @Autowired
    DireccionService direccionService;

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> findDirectionById(@PathVariable("id") Integer idDireccion) {
        return ResponseEntity.status(HttpStatus.OK).body(direccionService.getDirectionById(idDireccion));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<DireccionDTO>> findAllDirectionsByIdCliente(@PathVariable("id") Integer idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(direccionService.getAllDirectionsByIdCliente(idCliente));
    }

    @PostMapping()
    public ResponseEntity<DireccionDTO> saveDirection(@RequestBody DireccionDTO direccionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.saveDirecction(direccionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> changeStatusDirection(@PathVariable("id") Integer idDireccion) {
        return ResponseEntity.status(HttpStatus.OK).body(direccionService.changeStatusDirection(idDireccion));
    }

    @PutMapping
    public ResponseEntity<DireccionDTO> updateDirection(@RequestBody DireccionDTO direccionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(direccionService.updateDirecction(direccionDTO));
    }
}