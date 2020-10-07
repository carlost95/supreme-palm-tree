package com.undec.corralon.controlador;

import com.undec.corralon.DTO.ClienteDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = clienteService.listarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardar( @RequestBody ClienteDTO cliente) throws Exception {
        Response response = clienteService.save(cliente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> update( @RequestBody ClienteDTO cliente) throws Exception {
        Response response = clienteService.update(cliente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Response> changeStatus( @PathVariable("id") Integer id) throws Exception {
        Response response = clienteService.changeStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
