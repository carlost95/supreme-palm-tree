package com.undec.corralon.controlador;

import com.undec.corralon.DTO.AjusteDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.Ajuste.AjusteException;
import com.undec.corralon.modelo.Ajuste;
import com.undec.corralon.service.AjusteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ajustes")
public class AjusteController {
    @Autowired
    AjusteService ajusteService;

    @GetMapping
    public ResponseEntity<List<Ajuste>> findAllSetting() {
        return ResponseEntity.status(HttpStatus.OK).body(ajusteService.findAllTheSetting());
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Ajuste>> findAllTheSettingEnabled() {
        return ResponseEntity.status(HttpStatus.OK).body(ajusteService.findAllSettingEnabled());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ajuste> findSettingById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ajusteService.findSettingById(id));
    }

    @PostMapping
    public ResponseEntity<AjusteDTO> saveSetting(@RequestBody AjusteDTO ajusteDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(ajusteService.saveSetting(ajusteDTO));
    }

//    @PutMapping
//    public ResponseEntity<Response> modificarAjuste(@RequestBody Ajuste ajuste) throws AjusteException {
//        Response response = ajusteService.modificarAjuste(ajuste);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Response> cambiarHabilitacionAjuste(@PathVariable("id") Integer id) throws AjusteException {
//        Response response = ajusteService.cambiarHabilitacionAjuste(id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
