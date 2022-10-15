package com.undec.corralon.controlador;

import com.undec.corralon.DTO.PedidoDTO;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.modelo.Remito;
import com.undec.corralon.service.PedidoService;
import com.undec.corralon.service.RemitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/remitos")
public class RemitoController {

    @Autowired
    RemitoService remitoService;

//    @GetMapping
//    public ResponseEntity<List<Remito>> findAllRemitos() {
//        return ResponseEntity.status(HttpStatus.OK).body(remitoService.findAllOrders());
//    }
//
//    @GetMapping("/entregados")
//    public ResponseEntity<List<Remito>> listOfOrdersHabilitation() {
//        return ResponseEntity.status(HttpStatus.OK).body(remitoService.findOrdersHabilitation());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<RemitoDTO> findOrderForId(@PathVariable Integer id) {
//        return ResponseEntity.status(HttpStatus.OK).body(remitoService.findOrderForId(id));
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<Remito> changeStatusRemito (@PathVariable Integer id) {
//        return ResponseEntity.status(HttpStatus.OK).body(remitoService.changueHabilityOrder(id));
//    }
}