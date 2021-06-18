package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToSaveException;
import com.undec.corralon.excepciones.Ajuste.AjusteException;
import com.undec.corralon.excepciones.Pedido.PedidoException;
import com.undec.corralon.modelo.Ajuste;
import com.undec.corralon.service.AjusteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/ajustes")
public class AjusteController {
    @Autowired
    AjusteService ajusteService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosAjustes(){
        Response response = ajusteService.obtenerTodosLosAjustes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerTodosAjustesHabilitados(){
        Response response = ajusteService.obtenerTodosLosAjustesHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerAjusteId(@PathVariable("id") Integer id){
        Response response = ajusteService.obtenerAjustePorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Response> saveAjuste(@RequestBody Ajuste ajuste) throws AjusteException {
        Response response = ajusteService.saveAjuste(ajuste);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Response> modificarAjuste(@RequestBody Ajuste ajuste) throws AjusteException {
        Response response = ajusteService.modificarAjuste(ajuste);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> cambiarHabilitacionAjuste(@PathVariable("id") Integer id) throws AjusteException {
        Response response = ajusteService.cambiarHabilitacionAjuste(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
