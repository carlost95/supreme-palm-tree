package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.reportes.ReporteBanco;
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

    @GetMapping("/export/{format}")
    public ResponseEntity<Response> exportReport(@PathVariable String format) {
        Response response = reporteBanco.exportReport(format);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
