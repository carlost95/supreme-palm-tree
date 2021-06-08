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
        Map<Integer, Integer> movimientosArticulos = new HashMap<Integer, Integer>();
        List<MovimientoArticulo> articulos = this.movimientoArticuloRepository.findAllByPedidoId_Id(idPedido);
        articulos.forEach(p -> {
            Integer idArticulo = p.getArticuloByIdArticulo().getIdArticulo();
            Integer movimiento = p.getMovimiento();
            if (movimiento == null)
                movimiento = 0;
            movimientosArticulos.put(idArticulo, movimiento);
        });

        response.setCode(200);
        response.setMsg("Movimiento de articulo por pedido");
        response.setData(movimientosArticulos);

        return response;

    }

    public Response obtenerMovimientosPorAjuste(Integer idAjuste) {

        Response response = new Response();
        Map<Integer, Integer> movimientosArticulos = new HashMap<Integer, Integer>();
        List<MovimientoArticulo> articulos = this.movimientoArticuloRepository.findAllByAjusteId_Id(idAjuste);
        articulos.forEach(p -> {
            Integer idArticulo = p.getArticuloByIdArticulo().getIdArticulo();
            Integer movimiento = p.getMovimiento();
            if (movimiento == null)
                movimiento = 0;
            movimientosArticulos.put(idArticulo, movimiento);
        });

        response.setCode(200);
        response.setMsg("Movimiento de articulo por ajuste");
        response.setData(movimientosArticulos);

        return response;

    }

    public Response obtenerStockArticulosActualPedido(Integer idTipoMov) {

        Response response = new Response();
        Map<Integer, Double> stock = new HashMap<Integer, Double>();
        List<Articulo> articulos = this.articuloRepository.findAll();
        List<MovimientoArticulo> movimientos = this.movimientoArticuloRepository.findAll();
        //VALOR ORIGINAL
//        String fechaPedido = this.pedidoRepository.findById(idTipoMov).get().getFecha();
        Timestamp fechaPedido = this.pedidoRepository.findById(idTipoMov).get().getFecha();

        movimientos = movimientos.stream().sorted(Comparator.comparing(MovimientoArticulo::getFecha)).collect(Collectors.toList());
        movimientos.stream().forEach(System.out::println);

        movimientos = movimientos.stream().filter(m -> m.getArticuloByIdArticulo().getIdArticulo() < idTipoMov).collect(Collectors.toList());
        System.out.println("----------------------");
        movimientos.stream().forEach(System.out::println);
//SE REALIZO REFACTOR PARA CAMBIAR LOS TIPÓS DE DATOS PARA LAS FECHAS DE UN AJUSTE
        articulos.forEach(p -> {
            Integer idArticulo = p.getIdArticulo();
            Double stockArt = this.movimientoArticuloRepository.stockPorArticulo(idArticulo, fechaPedido);
            if (stockArt == null)
                stockArt = 0.0;
            stock.put(idArticulo, stockArt);
        });


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
        //VALORES ORGINALES
//        String fechaAjuste = this.ajusteRepository.findById(idTipoMov).get().getFecha();
        Timestamp fechaAjuste = this.ajusteRepository.findById(idTipoMov).get().getFecha();

        movimientos = movimientos.stream().sorted(Comparator.comparing(MovimientoArticulo::getFecha)).collect(Collectors.toList());
        movimientos.stream().forEach(System.out::println);
        movimientos = movimientos.stream().filter(m -> m.getArticuloByIdArticulo().getIdArticulo() < idTipoMov).collect(Collectors.toList());
        articulos.forEach(p -> {
            Integer idArticulo = p.getIdArticulo();
            Double stockArt = this.movimientoArticuloRepository.stockPorArticulo(idArticulo, fechaAjuste);
            if (stockArt == null)
                stockArt = 0.0;
            stock.put(idArticulo, stockArt);
        });
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
//        movimientoArticulo.setFecha(movimientoArticuloDTO.getFecha() + horaDeCarga);
//        movimientoArticulo.setMovimiento(movimientoArticuloDTO.getMovimiento());
//        movimientoArticulo.setArticuloId(this.articuloRepository.findById(movimientoArticuloDTO.getArticuloId()).get());
//        movimientoArticulo.setPedidoId(this.pedidoRepository.findById(movimientoArticuloDTO.getPedidoId()).get());

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
        //VERIFICAR SETEO DE DATOS
//        movimientoArticulo.setFecha(movimientoArticuloDTO.getFecha() + horaDeCarga);
//        movimientoArticulo.setMovimiento(movimientoArticuloDTO.getMovimiento());
//        movimientoArticulo.setArticuloId(this.articuloRepository.findById(movimientoArticuloDTO.getArticuloId()).get());
//        movimientoArticulo.setAjusteId(this.ajusteRepository.findById(movimientoArticuloDTO.getAjusteId()).get());

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
            //VERIFICAR HABILITACION
//            if (art.getHabilitacion() == true) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String fecha = LocalDateTime.now().format(formatter).toString();
//                System.out.println(fecha);
//                Double mov = this.movimientoArticuloRepository.stockPorArticulo(art.getId(), fecha);
//                System.out.println(mov);
//                if (mov == null)
//                    mov = 0.0;
//                movimientoArticulo.add(mov);
//            }

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
            //HABILITACION VERIFICAR
//            if (p.getHabilitacion()) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String fecha = LocalDateTime.now().format(formatter).toString();
//                System.out.println(fecha);
//                Double mov = this.movimientoArticuloRepository.stockPorArticulo(p.getIdArticulo(), fecha);
//                System.out.println(mov);
//                if (mov == null)
//                    mov = 0.0;
//                movimientoArticulo.add(mov);
//            }

        });


        response.setCode(200);
        response.setMsg("Movimientos");
        response.setData(movimientoArticulo);

        return response;
    }
}
