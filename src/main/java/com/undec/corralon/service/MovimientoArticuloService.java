package com.undec.corralon.service;

import com.undec.corralon.DTO.MovimientoArticuloDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.MovimientoArticulo;
import com.undec.corralon.repository.AjusteRepository;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.MovimientoArticuloRepository;
import com.undec.corralon.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovimientoArticuloService {

    @Autowired
    MovimientoArticuloRepository movimientoArticuloRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    AjusteRepository ajusteRepository;

    @Autowired
    ArticuloRepository articuloRepository;

    public Response obtenerMovimientosPorPedido(Integer idPedido) {

        Response response = new Response();
//        Map<Integer, Integer> movimientosArticulos = new HashMap<Integer, Integer>();
//        List<MovimientoArticulo> articulos = this.movimientoArticuloRepository.findAllByPedidoByIdPedido(idPedido);
//        articulos.forEach(p -> {
//            Integer idArticulo = p.getArticuloByIdArticulo().getIdArticulo();
//            Integer movimiento = p.getMovimiento();
//            if (movimiento == null)
//                movimiento = 0;
//            movimientosArticulos.put(idArticulo, movimiento);
//        });
//
        response.setCode(200);
        response.setMsg("Movimiento de articulo por pedido");
//        response.setData(movimientosArticulos);

        return response;

    }

    public Response obtenerMovimientosPorAjuste(Integer idAjuste) {

        Response response = new Response();
//        Map<Integer, Integer> movimientosArticulos = new HashMap<Integer, Integer>();
//        List<MovimientoArticulo> articulos = this.movimientoArticuloRepository.findAllByAjusteByIdAjuste(idAjuste);
//        articulos.forEach(p -> {
//            Integer idArticulo = p.getArticuloByIdArticulo().getIdArticulo();
//            Integer movimiento = p.getMovimiento();
//            if (movimiento == null)
//                movimiento = 0;
//            movimientosArticulos.put(idArticulo, movimiento);
//        });
//
        response.setCode(200);
        response.setMsg("Movimiento de articulo por ajuste");
//        response.setData(movimientosArticulos);

        return response;

    }

    public Response obtenerStockArticulosActualPedido(Integer idTipoMov) {

        Response response = new Response();
        Map<Integer, Double> stock = new HashMap<Integer, Double>();
        List<Articulo> articulos = this.articuloRepository.findAll();
        List<MovimientoArticulo> movimientos = this.movimientoArticuloRepository.findAll();
        Timestamp fechaPedido = Timestamp.valueOf(this.pedidoRepository.findById(idTipoMov).get().getFecha());
        movimientos = movimientos.stream().sorted(Comparator.comparing(MovimientoArticulo::getFecha)).collect(Collectors.toList());
        movimientos.stream().forEach(System.out::println);

        movimientos = movimientos.stream().filter(m -> m.getArticuloByIdArticulo().getIdArticulo() < idTipoMov).collect(Collectors.toList());
        movimientos.stream().forEach(System.out::println);
//        PROBLEMA CON LA NUEVA DISPOSICION CON LA BASE DE DATOS
//        articulos.forEach(p -> {
//            Integer idArticulo = p.getIdArticulo();
//            Double stockArt = this.movimientoArticuloRepository.stockPorArticulo(idArticulo, fechaPedido);
//            if (stockArt == null)
//                stockArt = 0.0;
//            stock.put(idArticulo, stockArt);
//        });


        response.setCode(200);
        response.setMsg("Articulos y stock");
        response.setData(stock);

        return response;

    }

    public Response obtenerStockArticulosActualAjuste(Integer idTipoMov) {
        Response response = new Response();
        Map<Integer, Double> stock = new HashMap<Integer, Double>();
        List<Articulo> articulos = this.articuloRepository.findAll();
        List<MovimientoArticulo> movimientos = this.movimientoArticuloRepository.findAll();
        Timestamp fechaAjuste = Timestamp.valueOf(this.ajusteRepository.findById(idTipoMov).get().getFecha());

        movimientos = movimientos.stream().sorted(Comparator.comparing(MovimientoArticulo::getFecha)).collect(Collectors.toList());
        movimientos.stream().forEach(System.out::println);
        movimientos = movimientos.stream().filter(m -> m.getArticuloByIdArticulo().getIdArticulo() < idTipoMov).collect(Collectors.toList());
//        PROBLEMAS CON LA NUEVA DISPOSICION CON LA BASE DE DATOS
//        articulos.forEach(p -> {
//            Integer idArticulo = p.getIdArticulo();
//            Double stockArt = this.movimientoArticuloRepository.stockPorArticulo(idArticulo, fechaAjuste );
//
//            if (stockArt == null)
//                stockArt = 0.0;
//            stock.put(idArticulo, stockArt);
//        });
        response.setCode(200);
        response.setMsg("Articulos y stock");
        response.setData(stock);

        return response;

    }

    public Response guardarMovimientoPedido(MovimientoArticuloDTO movimientoArticuloDTO) {
        Response response = new Response();
        MovimientoArticulo movimientoArticulo = new MovimientoArticulo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaDeCarga = LocalDateTime.now().format(formatter).toString();
        horaDeCarga = horaDeCarga.substring(10, horaDeCarga.length());
//        VERIFIACR SETEO DE DATOS
        movimientoArticulo.setFecha(movimientoArticuloDTO.getFecha() + horaDeCarga);
        movimientoArticulo.setMovimiento(movimientoArticuloDTO.getMovimiento());
        movimientoArticulo.setArticuloByIdArticulo(this.articuloRepository.findById(movimientoArticuloDTO.getIdArticulo()).get());
//        REMUEVO POR NUEVA DISPOSISCION EN LA BASE DE DATOS
//        movimientoArticulo.setPedidoByIdPedido(this.pedidoRepository.findById(movimientoArticuloDTO.getIdPedido()).get());

        movimientoArticulo = this.movimientoArticuloRepository.save(movimientoArticulo);

        response.setCode(200);
        response.setMsg("Movimiento guardado");
        response.setData(movimientoArticulo);
        return response;
    }

    public Response guardarMovimientoAjuste(MovimientoArticuloDTO movimientoArticuloDTO) {
        Response response = new Response();
        MovimientoArticulo movimientoArticulo = new MovimientoArticulo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaDeCarga = LocalDateTime.now().format(formatter).toString();
        horaDeCarga = horaDeCarga.substring(10, horaDeCarga.length());
        movimientoArticulo.setFecha(movimientoArticuloDTO.getFecha() + horaDeCarga);
        movimientoArticulo.setMovimiento(movimientoArticuloDTO.getMovimiento());
        movimientoArticulo.setArticuloByIdArticulo(this.articuloRepository.findById(movimientoArticuloDTO.getIdArticulo()).get());
//        REMUEVO Y VERIFICAR  POR NUEVA DISPÓSICION EN LA BASE DE DATOS
//        movimientoArticulo.setAjusteByIdAjuste(this.ajusteRepository.findById(movimientoArticuloDTO.getIdAjuste()).get());

        movimientoArticulo = this.movimientoArticuloRepository.save(movimientoArticulo);

        response.setCode(200);
        response.setMsg("Movimientos de ajuste guardado");
        response.setData(movimientoArticulo);
        return response;
    }

    public Response obtenerTodosLosMoviemientosPedido() {
        Response response = new Response();

        List<Articulo> articulos = this.articuloRepository.findAll();
        List<Double> movimientoArticulo = new ArrayList<Double>();

        articulos.stream().forEach(art -> {
            if (art.getHabilitado() == true) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Timestamp fecha = Timestamp.valueOf(LocalDateTime.now().format(formatter).toString());
//                PROBLEMAS CON LA NUEVA DISPOSICION DE LA BASE DE DATOS
//                Double mov = this.movimientoArticuloRepository.stockPorArticulo(art.getIdArticulo(), fecha);
//                if (mov == null)
//                    mov = 0.0;
//                movimientoArticulo.add(mov);
            }

        });
        response.setCode(200);
        response.setMsg("Movimientos");
        response.setData(movimientoArticulo);

        return response;
    }

    public Response obtenerTodosLosMoviemientosAjuste() {
        Response response = new Response();
        List<Articulo> articulos = this.articuloRepository.findAll();

        List<Double> movimientoArticulo = new ArrayList<Double>();

        articulos.stream().forEach(p -> {
            if (p.getHabilitado()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Timestamp fecha = Timestamp.valueOf(LocalDateTime.now().format(formatter).toString());
//                PROBLEMAS CON LA NUEVA DISPOSICION DE LA BASE DE DATOS
//                Double mov = this.movimientoArticuloRepository.stockPorArticulo(p.getIdArticulo(), fecha);
//                System.out.println(mov);
//                if (mov == null)
//                    mov = 0.0;
//                movimientoArticulo.add(mov);
            }
        });

        response.setCode(200);
        response.setMsg("Movimientos ajustes");
        response.setData(movimientoArticulo);

        return response;
    }
}
