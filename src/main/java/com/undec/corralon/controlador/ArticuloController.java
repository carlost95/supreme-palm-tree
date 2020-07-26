package com.undec.corralon.controlador;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.articulo.ArticuloException;
import com.undec.corralon.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosLosArticulos(){
        Response response = articuloService.obtenerTodosLosArticulos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerTodosLosArticulosHabilitados(){
        Response response = articuloService.obtenerTodosLosArticulosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerArticuloPorId(@PathVariable("id") Integer id){
        Response response = articuloService.obtenerArticuloPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> crearArticulo(@RequestBody ArticuloDTO articuloDTO) throws ArticuloException {
        Response response = articuloService.crearArticulo(articuloDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizarArticulo(@RequestBody ArticuloDTO articuloDTO) throws ArticuloException {
        Response response = articuloService.actualizarArticulo(articuloDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> bajaArticulo(@PathVariable("id") Integer id) throws ArticuloException {
        Response response = articuloService.darBajaArticulo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
