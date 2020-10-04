package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.AjusteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/ajustes")
public class AjusteController {
    AjusteService ajusteService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosAjustes(){
        Response response = ajusteService.obtenerTodosAjustes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
