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

@CrossOrigin("*")
@RestController
@RequestMapping("/bancos")

public class BancoController {
    @Autowired
    BancoService bancoService;

    @GetMapping
    public ResponseEntity<Response> listOfBank(){
        Response response = bancoService.listOfBank();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> listOfBankHalilitation(){
        Response response = bancoService.listOfBankHalilitation();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER') || hasRole('ROLE_GERENTE')")
    @PostMapping
    public ResponseEntity<Response> saveOfBank(@Valid @RequestBody BancoRequest bancoRequest) {
        Response response = bancoService.saveOfBank(bancoRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> listOfBankForId(@PathVariable("id") Integer id) {
        Response response = bancoService.listOfBankForId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER') || hasRole('ROLE_GERENTE')")
    @PutMapping
    public ResponseEntity<Banco> updatedBank(@Valid @RequestBody Banco banco) {
//        Response response = bancoService.updatedBank(banco);
        return new ResponseEntity<>(bancoService.updatedBank(banco), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Response> changeOfHabilitationBank(@PathVariable("id") Integer id){
        Response response = bancoService.changeOfHabilitationBank(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
