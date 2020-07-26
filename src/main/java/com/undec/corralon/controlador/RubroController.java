package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.rubro.RubroException;
import com.undec.corralon.modelo.Rubro;
import com.undec.corralon.service.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rubro")
public class RubroController {

    @Autowired
    RubroService rubroService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosLosRubros(){
        Response response = rubroService.obtenerTodosLosRubros();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerRubroPorId(@PathVariable("id") Integer id) throws RubroException {
        Response response = rubroService.obtenerPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerRubrosHabilitados() throws RubroException {
        Response response = rubroService.obtenerTodosLosRubrosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> crearRubro(@RequestBody Rubro rubro) throws RubroException {
        Response response = rubroService.crearRubro(rubro);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Response> actualizarRubro(@RequestBody Rubro rubro) throws RubroException {
        Response response = rubroService.actualizarRubro(rubro);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> darBajaRubro(@PathVariable Integer id) throws RubroException {
        Response response = rubroService.bajaRubro(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
