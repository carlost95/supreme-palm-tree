package com.undec.corralon.controlador;

import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClientById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClient() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAllClient());
    }
    @GetMapping("/enabled")
    public ResponseEntity<List<Cliente>> findAllClientEnabled() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAllClientEnabled());
    }

    @PostMapping
    public ResponseEntity<Cliente> saveClient( @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.saveClient(cliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> updatedClient( @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updatedClient(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> changeStatus( @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.changeStatus(id));
    }
}
