package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.reportes.*;
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

    @Autowired
    ReporteProveedor reporteProveedor;

    @Autowired
    ReporteRubro reporteRubro;

    @Autowired
    ReporteUnidadMedida reporteUnidadMedida;

    @Autowired
    ReportSubRubro reportSubRubro;

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
    @GetMapping("/proveedor")
    public ResponseEntity<Response> proveedorReport() {
        Response response = reporteProveedor.exportReport();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/rubro")
    public  ResponseEntity<Response> rubroReport(){
        Response response= reporteRubro.exportReport();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/unidad-medida")
    public  ResponseEntity<Response> unidadMedidaReport(){
        Response response= reporteUnidadMedida.exportReport();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/sub-rubro")
    public  ResponseEntity<Response> subRubroReport(){
        Response response= reportSubRubro.exportReport();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
