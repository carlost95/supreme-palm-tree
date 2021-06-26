package com.undec.corralon.controlador;

import com.undec.corralon.DTO.BancoProveedorDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.BancoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/banco-proveedor")
public class BancoProveedoreController {
    @Autowired
    BancoProveedorService bancoProveedorService;

    @GetMapping
    public ResponseEntity<Response> obtenerdatosBnacoProveedor() throws Exception{
        Response response = bancoProveedorService.obtenerBancosProveedores();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerdatosBancoProveedorId(@PathVariable ("id") Integer id) throws Exception{
        Response response= bancoProveedorService.obtenerDatosBancoProveedorById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> createBancoProveedor(@RequestBody BancoProveedorDTO bancoProveedorDTO) throws Exception {
        Response response = bancoProveedorService.createBancoProveedor(bancoProveedorDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}