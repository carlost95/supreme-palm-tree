package com.undec.corralon.controlador;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.Pedido.PedidoException;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Response> obtenerTodosLosPedidos(){
        Response response = pedidoService.obtenerTodosLosPedidos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<Response> obtenerTodosLosPedidosHabilitados(){
        Response response = pedidoService.obtenerPedidosHabilitados();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerPedidoId(@PathVariable("id") Integer id){
        Response response = pedidoService.obtenerPedidoPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> guardarPedido(@RequestBody Pedido pedido) throws PedidoException {
        Response response = pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> modificarPedido(@RequestBody Pedido pedido) throws PedidoException {
        Response response = pedidoService.modificarPedido(pedido);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> eliminarPedido(@PathVariable("id") Integer id) throws PedidoException {
        Response response = pedidoService.darBajaPedido(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}