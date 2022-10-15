package com.undec.corralon.controlador;

import com.undec.corralon.DTO.VentaConsultDTO;
import com.undec.corralon.DTO.VentaDTO;
import com.undec.corralon.modelo.Venta;
import com.undec.corralon.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> findAllSales() {
        return ResponseEntity.status(HttpStatus.OK).body(ventaService.findAllSales());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VentaConsultDTO> findSaleById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ventaService.findSaleById(id));
    }
    @PostMapping
    public ResponseEntity<VentaDTO> saveSale(@RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(ventaService.saveSale(ventaDTO));
    }
}
