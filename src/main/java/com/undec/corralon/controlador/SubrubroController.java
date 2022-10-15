package com.undec.corralon.controlador;

import com.undec.corralon.DTO.SubrubroDTO;
import com.undec.corralon.service.SubrubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/sub-rubros")
public class SubrubroController {

    @Autowired
    SubrubroService subrubroService;

    @GetMapping
    public ResponseEntity<List<SubrubroDTO>> obtenerTodosLosSubrubros(){
        return new ResponseEntity<>(subrubroService.buscarTodosLosSubrubros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubrubroDTO> obtenerSubrubroPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(subrubroService.obtenerSubrubroPorId(id), HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<SubrubroDTO>> obtenerTodosLosSubrubrosHabilitados(){
        return new ResponseEntity<>(subrubroService.buscarTodosLosSubrubrosHabilitados(), HttpStatus.OK);
    }

    @GetMapping("/rubro/{id}")
    public ResponseEntity<List<SubrubroDTO>> obtenerTodosPorRubro(@PathVariable("id") Integer id){
        return new ResponseEntity<>(subrubroService.obtenerSubrubroPorRubro(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubrubroDTO> guardarSubrubro(@RequestBody SubrubroDTO subrubroDTO) {
        return new ResponseEntity<>(subrubroService.guardarSubrubro(subrubroDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubrubroDTO> actualizarSubrubro(@RequestBody SubrubroDTO subrubroDTO) {
        return new ResponseEntity<>(subrubroService.actualizarSubrubro(subrubroDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubrubroDTO> cambiarHabilitacion(@PathVariable("id") Integer id){
        return new ResponseEntity<>(subrubroService.cambiarHabilitacion(id), HttpStatus.OK);
    }
}
