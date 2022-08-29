package com.undec.corralon.controlador;

import com.undec.corralon.DTO.DistritoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Distrito;
import com.undec.corralon.service.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/distritos")
public class  DistritoController {

    @Autowired
    DistritoService distritoService;

    @GetMapping
    public ResponseEntity<List <DistritoDTO>> listAllDistrict(){
        return ResponseEntity.status(HttpStatus.OK).body(distritoService.listAllDistrict());
    }

    @GetMapping("/habilitado")
    public ResponseEntity<List<DistritoDTO>> listAllDistrictHabilitation(){
        return ResponseEntity.status(HttpStatus.OK).body(distritoService.listAllDistrictHabilitation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritoDTO> findDistrictById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(distritoService.findDistrictById(id));
    }

    @PostMapping
    public ResponseEntity<DistritoDTO> saveDistrict(@RequestBody DistritoDTO distritoDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(distritoService.saveDistrict(distritoDTO));
    }

    @PutMapping
    public ResponseEntity<DistritoDTO> updatedDistrito(@RequestBody DistritoDTO  distritoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(distritoService.updateDistrict(distritoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistritoDTO> changeStatus(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(distritoService.changeStatus(id));
    }

}
