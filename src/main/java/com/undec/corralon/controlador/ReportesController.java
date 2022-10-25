package com.undec.corralon.controlador;

import com.undec.corralon.DTO.DataReporte;
import com.undec.corralon.DTO.FechaReporte;
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

    @PostMapping("/ventas")
    public ResponseEntity <List<DataReporte>> reporteFechasVenta(@RequestBody FechaReporte fechaReporte){
        return new ResponseEntity<>(reporteService.obtenerVentas(fechaReporte.getFechaInicial(), fechaReporte.getFechaFinal()), HttpStatus.OK);
    }
    @PostMapping("/remitos")
    public ResponseEntity <List<DataReporte>> reporteFechasRemito(@RequestBody FechaReporte fechaReporte){
        return new ResponseEntity<>(reporteService.obtenerRemitos(fechaReporte.getFechaInicial(), fechaReporte.getFechaFinal()), HttpStatus.OK);
    }

}
