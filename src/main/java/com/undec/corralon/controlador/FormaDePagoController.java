package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.FormaPago;
import com.undec.corralon.service.FormaDePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/forma-pago")
public class FormaDePagoController {

    @Autowired
    FormaDePagoService formaDePagoService;

    @GetMapping
    public ResponseEntity<Response> obtenerFormasDePago(){
        Response response = formaDePagoService.obtenerTodasLasFormasDePago();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerFormasDePagoPorId(@PathVariable("id") Integer id){
        Response response = formaDePagoService.obtenerFormasDePagoPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerHabilitados(){
        Response response = formaDePagoService.obtenerHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> crearFormaDePago(@RequestBody FormaPago formaPago) throws Exception {
        Response response = formaDePagoService.agregarFormaDePago(formaPago);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizarFormaDePago(@RequestBody FormaPago formaPago) throws Exception {
        Response response = formaDePagoService.actualizarFormaDePago(formaPago);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> eliminarFormaDePago(@PathVariable Integer id) throws Exception {
        Response response = formaDePagoService.darDeBaja(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
