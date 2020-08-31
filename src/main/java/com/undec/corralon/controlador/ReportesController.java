package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.reportes.ReporteArticulo;
import com.undec.corralon.reportes.ReporteBanco;
import com.undec.corralon.reportes.ReporteMarca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    ReporteBanco reporteBanco;

    @Autowired
    ReporteArticulo reporteArticulo;

    @Autowired
    ReporteMarca reporteMarca;

    @GetMapping("/banco")
    public ResponseEntity<Response> bancoReport() {
        Response response = reporteBanco.exportReport();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/articulo")
    public ResponseEntity<Response> articuloReport() {
        Response response = reporteArticulo.exportReport();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/marca")
    public ResponseEntity<Response> marcaReport() {
        Response response = reporteMarca.exportReport();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
