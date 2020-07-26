package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.cliente.ClienteErrorToSaveException;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.PruebaUser;
import com.undec.corralon.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    /**
     *
     */
    @Autowired
    ClienteService clienteService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = clienteService.listarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("habilitados")
    public ResponseEntity<Response>listarTodosHabilitados() throws Exception {
        Response response= clienteService.listarTodosHabilitados();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Response response = clienteService.listarPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardar(@Valid @RequestBody Cliente cliente) throws Exception {
        Response response = clienteService.guardar(cliente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@Valid @RequestBody Cliente cliente) throws Exception {
        Response response = clienteService.actualizar(cliente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> eliminar(@PathVariable("id") Integer id) throws Exception {
        Response response = clienteService.darDeBaja(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*Prueba de autenticacion*/
//    @GetMapping(produces = "application/json")
//    @RequestMapping({ "/login" })
//    public PruebaUser validateLogin() {
//        return new PruebaUser("Usuario Validado exitosamente");
//    }
}
