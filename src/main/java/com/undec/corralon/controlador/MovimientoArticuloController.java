package com.undec.corralon.controlador;

import com.undec.corralon.DTO.MovimientoArticuloDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.service.MovimientoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/movimientos")
public class MovimientoArticuloController {

    @Autowired
    MovimientoArticuloService movimientoArticuloService;
//    PROBLEMAS CON LA NUEVA DISPOSICION DE LA BASE DE DATOS
//
//    @GetMapping("/pedido/{id}")
//    public ResponseEntity<Response> obtenerMovimientosPreviosPedido(@PathVariable("id") Integer idTipoMov) {
//        Response response = movimientoArticuloService.obtenerStockArticulosActualPedido(idTipoMov);
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//    @GetMapping("/ajuste/{id}")
//    public ResponseEntity<Response> obtenerMovimientosPreviosAjuste(@PathVariable("id") Integer idTipoMov) {
//        Response response = movimientoArticuloService.obtenerStockArticulosActualAjuste(idTipoMov);
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/stock-pedido/{id}")
//    public ResponseEntity<Response> obtenerStockArticuloPedido(@PathVariable("id") Integer idPedido) {
//        Response response = movimientoArticuloService.obtenerMovimientosPorPedido(idPedido);
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//    @GetMapping("/stock-ajuste/{id}")
//    public ResponseEntity<Response> obtenerStockArticuloAjuste(@PathVariable("id") Integer idAjuste) {
//        Response response = movimientoArticuloService.obtenerMovimientosPorAjuste(idAjuste);
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/stock-pedido")
//    public ResponseEntity<Response> obtenerStockTodosArticulosPedidos() {
//        Response response = movimientoArticuloService.obtenerTodosLosMoviemientosPedido();
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//    @GetMapping("/stock-ajuste")
//    public ResponseEntity<Response> obtenerStockTodosArticulosAjustes() {
//        Response response = movimientoArticuloService.obtenerTodosLosMoviemientosAjuste();
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/pedidos")
//    public ResponseEntity<Response> guardarMovimientoPedido(@Valid @RequestBody MovimientoArticuloDTO movimientoArticuloDTO) {
//        Response response = movimientoArticuloService.guardarMovimientoPedido(movimientoArticuloDTO);
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/ajustes")
//    public ResponseEntity<Response> guardarMovimientoAjuste(@Valid @RequestBody MovimientoArticuloDTO movimientoArticuloDTO) {
//        Response response = movimientoArticuloService.guardarMovimientoAjuste(movimientoArticuloDTO);
//        return new ResponseEntity<Response>(response, HttpStatus.OK);
//    }

}
