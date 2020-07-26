package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.unidadMedida.UnidadMedidaException;
import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/unidad-medida")
public class UnidadMedidaController {

    @Autowired
    UnidadMedidaService unidadMedidaService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodasLasUnidadesDeMedida(){
        Response response = unidadMedidaService.obtenerTodasLasUnidadesDeMedida();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerUnidadMedidaPorId(@PathVariable("id") Integer id){
        Response response = unidadMedidaService.obtenerUnidadMedidaPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerUnidadesDeMedidaHabilitadas(){
        Response response = unidadMedidaService.obtenerUnidadMedidaHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Response> crearUnidadMedida(@RequestBody UnidadMedida unidadMedida) throws UnidadMedidaException {
        Response response = unidadMedidaService.crearUnidadMedida(unidadMedida);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Response> modificarUnidadMedida(@RequestBody UnidadMedida unidadMedida) throws UnidadMedidaException {
        Response response = unidadMedidaService.actualizarUnidadMedida(unidadMedida);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Response> deshabilitarUnidadMedida(@PathVariable("id") Integer id) throws UnidadMedidaException {
        Response response = unidadMedidaService.eliminarUnidadMedida(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
