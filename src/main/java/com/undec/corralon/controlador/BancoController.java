package com.undec.corralon.controlador;

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
//ROLE permisos a configurar  @PreAuthorizer

public class BancoController {
    @Autowired
    BancoService bancoService;

    @GetMapping
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = bancoService.listarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> listarTodosHabilitados() throws Exception {
        Response response = bancoService.listarTodosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Response> guardar(@Valid @RequestBody Banco banco) throws Exception {
        Response response = bancoService.guardarBanco(banco);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Response response = bancoService.obtenerBancoPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Response> actualizar(@Valid @RequestBody Banco banco) throws Exception {
        Response response = bancoService.actualizarBanco(banco);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Response> cambiarHabilitacion(@PathVariable("id") Integer id) throws Exception {
        Response response = bancoService.cambiarHabilitacion(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
