package com.undec.corralon.controlador;

import com.undec.corralon.DTO.BancoRequest;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.service.BancoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/bancos")

public class BancoController {
    @Autowired
    BancoService bancoService;

    @GetMapping
    public ResponseEntity<List<Banco>> listOfBank(){
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.listOfBank());
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Banco>> listOfBankHalilitation(){
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.listOfBankHalilitation());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER') || hasRole('ROLE_GERENTE')")
    @PostMapping
    public ResponseEntity<Banco> saveOfBank(@Valid @RequestBody Banco banco) {
        return new ResponseEntity<>(bancoService.saveOfBank(banco),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Banco> listOfBankForId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.listOfBankForId(id));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PutMapping
    public ResponseEntity<Banco> updatedBank(@Valid @RequestBody Banco banco) {
        return new ResponseEntity<>(bancoService.updatedBank(banco),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')|| hasRole('GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<Banco> changeOfHabilitationBank(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.changeOfHabilitationBank(id));
    }
}
