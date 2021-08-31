package com.undec.corralon.controlador;

import com.undec.corralon.DTO.PedidoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.pedido.PedidoException;
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
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAllPedidos());
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
    public ResponseEntity<PedidoDTO> saveOrder(@RequestBody PedidoDTO pedidoDTO) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.saveOrder(pedidoDTO));
    }

//    @PutMapping
//    public ResponseEntity<Response> modificarPedido(@RequestBody Pedido pedido) throws PedidoException {
//        Response response = pedidoService.modificarPedido(pedido);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Response> eliminarPedido(@PathVariable("id") Integer id) throws PedidoException {
//        Response response = pedidoService.darBajaPedido(id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}