package com.undec.corralon.controlador;

import com.undec.corralon.DTO.ProveedorDTO;
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
    public ResponseEntity<Response> listOfAllSuppliers() {
        Response response = proveedorService.listOfAllSuppliers();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/habilitados")
    public ResponseEntity<Response> listOfSuppliersHabilitation(){
        Response response = proveedorService.listOfSuppliersHabilitation();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> listSuppliersForId(@PathVariable Integer id){
           Response response = proveedorService.listSuppliersForId(id);
           return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> saveSupplier(@Valid @RequestBody ProveedorDTO proveedorDTO) {
        Response response = proveedorService.saveSupplier(proveedorDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> updatedSupplier(@Valid @RequestBody ProveedorDTO proveedorDTO){
        Response response = proveedorService.updatedSupplier(proveedorDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> habilitationChange(@PathVariable("id") Integer id){
        Response response = proveedorService.habilitationChange(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
