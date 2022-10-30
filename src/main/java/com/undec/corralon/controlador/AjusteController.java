package com.undec.corralon.controlador;

import com.undec.corralon.DTO.AjusteDTO;
import com.undec.corralon.modelo.Ajuste;
import com.undec.corralon.service.AjusteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<AjusteDTO> findSettingById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ajusteService.findSettingById(id));
    }

    @PostMapping
    public ResponseEntity<AjusteDTO> saveSetting(@RequestBody AjusteDTO ajusteDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(ajusteService.saveSetting(ajusteDTO));
    }

}
