package com.undec.corralon.controlador;

import com.undec.corralon.modelo.Rubro;
import com.undec.corralon.service.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rubros")
public class RubroController {

    @Autowired
    RubroService rubroService;

    @GetMapping
    public ResponseEntity<List<Rubro>> obtenerTodosLosRubros(){
        return new ResponseEntity<>(rubroService.obtenerTodosLosRubros(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Rubro> obtenerRubroPorId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(rubroService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Rubro>> obtenerRubrosHabilitados() {
        return new ResponseEntity<>(rubroService.obtenerTodosLosRubrosHabilitados(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Rubro> crearRubro(@RequestBody Rubro rubro) {
        return new ResponseEntity<>(rubroService.crearRubro(rubro), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Rubro> actualizarRubro(@RequestBody Rubro rubro) {
        return new ResponseEntity<>(rubroService.actualizarRubro(rubro), HttpStatus.OK);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Rubro> cambiarHabilitacion(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(rubroService.cambiarHabilitacion(id), HttpStatus.OK);
    }
}
