package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.VentaPorMes;
import com.undec.corralon.reportes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/venta-por-mes")
    public ResponseEntity<List<VentaPorMes>> obtenerVentasPorMes(){
        return new ResponseEntity<>(reporteService.obtenerVentasPorMes(), HttpStatus.OK);
    }
}
