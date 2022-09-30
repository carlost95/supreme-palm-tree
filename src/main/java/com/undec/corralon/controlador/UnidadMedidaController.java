package com.undec.corralon.controlador;

import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/unidad-medida")
public class UnidadMedidaController {

    @Autowired
    UnidadMedidaService unidadMedidaService;

    @GetMapping
    public ResponseEntity<List<UnidadMedida>> obtenerTodasLasUnidadesDeMedida(){
        return new ResponseEntity<>(unidadMedidaService.obtenerTodasLasUnidadesDeMedida(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> obtenerUnidadMedidaPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(unidadMedidaService.obtenerUnidadMedidaPorId(id), HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<UnidadMedida>> obtenerUnidadesDeMedidaHabilitadas(){
        return new ResponseEntity<>(unidadMedidaService.obtenerUnidadMedidaHabilitados(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UnidadMedida> crearUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        return new ResponseEntity<>(unidadMedidaService.crearUnidadMedida(unidadMedida), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<UnidadMedida> modificarUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        return new ResponseEntity<>(unidadMedidaService.actualizarUnidadMedida(unidadMedida), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> cambiarHabilitacion(@PathVariable("id") Integer id)  {
        return new ResponseEntity<>(unidadMedidaService.cambiarHabilitacion(id), HttpStatus.OK);
    }

}
