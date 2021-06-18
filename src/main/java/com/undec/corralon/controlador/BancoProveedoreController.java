package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.BancoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/datos")
public class BancoProveedoreController {
    @Autowired
    BancoProveedorService bancoProveedorService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosAjustes() throws Exception{
        Response response = bancoProveedorService.obtenerBancosProveedores();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}