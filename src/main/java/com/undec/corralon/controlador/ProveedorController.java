package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<Response> listarTodos() throws Exception {
        Response response = proveedorService.listarTodos();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/habilitados")
    public ResponseEntity<Response> listarTodosHabilitados() throws Exception {
        Response response = proveedorService.listarTodosHabilitados();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Response response = proveedorService.listarPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardar(@Valid @RequestBody Proveedor proveedor) throws Exception {
        Response response = proveedorService.guardarProveedor(proveedor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@Valid @RequestBody Proveedor proveedor) throws Exception {
        Response response = proveedorService.actualizarProveedor(proveedor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> eliminar(@PathVariable("id") Integer id) throws Exception {
        Response response = proveedorService.darBajaProveedor(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
