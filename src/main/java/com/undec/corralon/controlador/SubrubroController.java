package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.SubrubroDTO;
import com.undec.corralon.excepciones.subrubro.SubRubroErrorToSaveException;
import com.undec.corralon.excepciones.subrubro.SubrubroException;
import com.undec.corralon.service.SubrubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/sub-rubros")
public class SubrubroController {

    @Autowired
    SubrubroService subrubroService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosLosSubrubros(){
        Response response = subrubroService.buscarTodosLosSubrubros();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerSubrubroPorId(@PathVariable("id") Integer id){
        Response response = subrubroService.obtenerSubrubroPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerTodosLosSubrubrosHabilitados(){
        Response response = subrubroService.buscarTodosLosSubrubrosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/rubro/{id}")
    public ResponseEntity<Response> obtenerTodosPorRubro(@PathVariable("id") Integer id){
        Response response = subrubroService.obtenerSubrubroPorRubro(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardarSubrubro(@RequestBody SubrubroDTO subrubroDTO) throws SubrubroException {
        Response response = subrubroService.guardarSubrubro(subrubroDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizarSubrubro(@RequestBody SubrubroDTO subrubroDTO) throws SubrubroException {
        Response response = subrubroService.actualizarSubrubro(subrubroDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> darBajaSubrubro(@PathVariable("id") Integer id) throws SubrubroException {
        Response response = subrubroService.darBajaSubrubro(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
