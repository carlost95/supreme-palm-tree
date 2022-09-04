package com.undec.corralon.controlador;

import com.undec.corralon.DTO.CuentaBancariaDTO;
import com.undec.corralon.modelo.CuentaBancaria;
import com.undec.corralon.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/cuentasBancarias")
public class CuentaBancariaController {
    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<List<CuentaBancariaDTO>> findAccountsByIdProveedor(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(cuentaBancariaService.findAccountByIdProveedor(id));
    }

    @PostMapping
    public ResponseEntity<CuentaBancariaDTO> createAccountBank(@Valid @RequestBody CuentaBancariaDTO cuentaBancariaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cuentaBancariaService.createAccountBank(cuentaBancariaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> findAccountsById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(cuentaBancariaService.findAccountById(id));
    }

    @PutMapping
    public ResponseEntity<CuentaBancariaDTO> updateAccountBank(@Valid @RequestBody CuentaBancariaDTO cuentaBancariaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cuentaBancariaService.updateAccountBank(cuentaBancariaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> changeStatusAccountBank(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(cuentaBancariaService.changeStatusAccountBank (id));
    }
}