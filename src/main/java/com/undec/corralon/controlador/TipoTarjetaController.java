package com.undec.corralon.controlador;

import com.undec.corralon.modelo.Tipotarjeta;
import com.undec.corralon.service.TipoTarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/tipo-tarjeta")
public class TipoTarjetaController {
    @Autowired
    TipoTarjetaService tipoTarjetaService;

    @GetMapping
    public ResponseEntity<List<Tipotarjeta>> findAllSetting() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoTarjetaService.findAllCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipotarjeta> findCardById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoTarjetaService.findCardById(id));
    }

    @PostMapping
    public ResponseEntity<Tipotarjeta> saveSetting(@RequestBody Tipotarjeta card) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoTarjetaService.saveCard(card));
    }

    @PutMapping
    public ResponseEntity<Tipotarjeta> modifyTypeCard(@RequestBody Tipotarjeta typeCard) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoTarjetaService.modifyTypeCard(typeCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipotarjeta> changeEnablementToTypeCard(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoTarjetaService.changeEnablementToTypeCard(id));
    }
}

