package com.undec.corralon.controlador;

import com.undec.corralon.modelo.CostoArticulo;
import com.undec.corralon.service.CostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/costos")
public class CostoController {
    @Autowired
    CostoService costoService;

    @GetMapping
    public ResponseEntity<CostoArticulo> obtenerTodosAjustes(){
        return ResponseEntity.status(HttpStatus.OK).body(costoService.findCostoArticulo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoArticulo> obtenerLosCostos(@PathVariable ("id") Integer id){
        return  ResponseEntity.status(HttpStatus.OK).body(costoService.findCostoForIdArticulo(id));
    }
    @GetMapping("/articulo/{id}")
        public ResponseEntity <List<CostoArticulo>>findAllCostoArticuloByIdArticulo(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(costoService.findAllCostoForIdArticulo(id));
    }

}

