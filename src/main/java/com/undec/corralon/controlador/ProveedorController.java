package com.undec.corralon.controlador;

import com.undec.corralon.DTO.ProveedorDTO;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listOfAllSuppliers() {
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.listOfAllSuppliers());
    }
    @GetMapping("/habilitados")
    public ResponseEntity<List<Proveedor>> listOfSuppliersHabilitation(){
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.listOfSuppliersHabilitation());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> listSuppliersForId(@PathVariable Integer id){
           return ResponseEntity.status(HttpStatus.OK).body(proveedorService.listSuppliersForId(id));
    }

    @PostMapping
    public ResponseEntity<Proveedor> saveSupplier(@Valid @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.saveSupplier(proveedorDTO));
    }

    @PutMapping
    public ResponseEntity<Proveedor> updatedSupplier(@Valid @RequestBody ProveedorDTO proveedorDTO){
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.updatedSupplier(proveedorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> habilitationChange(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.habilitationChange(id));
    }
}
