package com.undec.corralon.controlador;


import com.undec.corralon.DTO.Distancia;
import com.undec.corralon.DTO.Parada;
import com.undec.corralon.modelo.Remito;
import com.undec.corralon.modelo.Ruta;
import com.undec.corralon.service.LogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService logisticaService;

    @PostMapping(value = "")
    public ResponseEntity<Ruta> obtenerRuta(@RequestBody Distancia distancias) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(logisticaService.obtenerRuta(distancias));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Parada>> obtenerParadas() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(logisticaService.obtenerParadas());
    }
}
