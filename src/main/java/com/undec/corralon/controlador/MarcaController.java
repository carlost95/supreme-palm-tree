package com.undec.corralon.controlador;

import com.undec.corralon.modelo.Marca;
import com.undec.corralon.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> listMark()  {
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.listMark());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.findById(id));
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Marca>> findAllHabilitation(){
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.listMarkHabilitation());
    }

    @PostMapping
    public ResponseEntity<Marca> saveMark(@Valid @RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.saveMark(marca));
    }

    @PutMapping
    public ResponseEntity<Marca> updatedMarca(@Valid @RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.updatedMark(marca));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Marca> changeHabilitation(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.changeHabilitation(id));
    }
}
