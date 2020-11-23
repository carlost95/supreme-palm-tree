package com.undec.corralon.controlador;

import com.undec.corralon.DTO.FacturaDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody FacturaDTO facturaDTO){
        Response response = this.facturaService.save(facturaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
