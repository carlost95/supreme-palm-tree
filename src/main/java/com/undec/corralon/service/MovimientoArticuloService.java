package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.DetallePedido;
import com.undec.corralon.modelo.MovimientoArticulo;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.MovimientoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MovimientoArticuloService {

    @Autowired
    MovimientoArticuloRepository movimientoArticuloRepository;

    @Autowired
    ArticuloRepository articuloRepository;


//    public Response obtenerStockArticulosActualPedido(Integer idTipoMov) {
//        Response response = new Response();
//        Map<Integer, Double> stock = new HashMap<Integer, Double>();
//        List<Articulo> articulos = this.articuloRepository.findAll();
//        List<MovimientoArticulo> movimientos = this.movimientoArticuloRepository.findAll();
//        Timestamp fechaPedido = Timestamp.valueOf(this.pedidoRepository.findById(idTipoMov).get().getFecha());
//        movimientos = movimientos.stream().sorted(Comparator.comparing(MovimientoArticulo::getFecha)).collect(Collectors.toList());
//        movimientos.stream().forEach(System.out::println);
//
//        movimientos = movimientos.stream().filter(m -> m.getArticuloByIdArticulo().getIdArticulo() < idTipoMov).collect(Collectors.toList());
//        movimientos.stream().forEach(System.out::println);
//        PROBLEMA CON LA NUEVA DISPOSICION CON LA BASE DE DATOS
//        articulos.forEach(p -> {
//            Integer idArticulo = p.getIdArticulo();
//            Double stockArt = this.movimientoArticuloRepository.stockPorArticulo(idArticulo, fechaPedido);
//            if (stockArt == null)
//                stockArt = 0.0;
//            stock.put(idArticulo, stockArt);
//        });
//        response.setCode(200);
//        response.setMsg("Articulos y stock");
//        response.setData(stock);
//
//        return response;
//
//    }

//    public Double findStockArticle(Integer idArticle) {
////        Articulo articulo = articuloRepository.findById(idArticle).
////                orElseThrow(
////                        () -> new NotFoundException("No existe articulo"));
////        System.out.println("\n------------------------------------FECHA: " + fechaActual + "-------------------------------------");
////        return movimientoArticuloRepository.stockPorArticulo(idArticle, fechaActual + horaDeCarga);
////    }

    public MovimientoArticulo saveMovimientoOrder(DetallePedido detallePedido) {
        MovimientoArticulo movimientoArticulo = new MovimientoArticulo();
        if (detallePedido == null) {
            throw new NotFoundException("\nWARNING: no existen detalles por registrar en movimientos");
        }
        movimientoArticulo.setArticuloByIdArticulo(detallePedido.getArticuloByIdArticulo());
        movimientoArticulo.setDetallePedidoByIdDetallePedido(detallePedido);
        movimientoArticulo.setFecha(detallePedido.getFecha());
        movimientoArticulo.setMovimiento(detallePedido.getCantidad());

        movimientoArticulo = movimientoArticuloRepository.save(movimientoArticulo);

        return movimientoArticulo;
    }


}
