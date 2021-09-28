package com.undec.corralon.controlador;

import com.undec.corralon.DTO.PedidoDTO;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAllPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAllOrders());
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Pedido>> listOfOrdersHabilitation() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findOrdersHabilitation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findOrderForId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findOrderForId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> saveOrder(@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.saveOrder(pedidoDTO));
    }

    @PutMapping
    public ResponseEntity<Pedido> modificateOrder(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.modifyOrder(pedido));
    }

    @PutMapping("{id}")
    public ResponseEntity<Pedido> changeHabilitationInTheOrder(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.changueHabilityOrder(id));
    }
}