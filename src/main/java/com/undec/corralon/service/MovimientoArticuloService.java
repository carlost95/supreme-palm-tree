package com.undec.corralon.service;

import com.undec.corralon.Util;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.MovimientoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class MovimientoArticuloService {

    @Autowired
    MovimientoArticuloRepository movimientoArticuloRepository;

    @Autowired
    ArticuloRepository articuloRepository;

    public Double findStockArticle(Integer idArticle) throws ParseException {
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Date fecha = Util.stringToDate(fechaActual);
        Articulo articulo = articuloRepository.findById(idArticle).
                orElseThrow(
                        () -> new NotFoundException("No existe articulo"));

        return movimientoArticuloRepository.stockPorArticulo(articulo, fecha);
    }

    public Double findStockByMovimientoArticle(Integer idArticulo, String fechaMovimiento) throws ParseException {
        if (fechaMovimiento == null)
            throw new NotFoundException("\nWARNING: la fecha del tipo movimiento no puede ser null");
        if (idArticulo == null) {
            throw new NotFoundException("\nWARNING: el identificador del articulo no puede ser null");
        }
        Date fecha = Util.stringToDate(fechaMovimiento);

        Articulo articulo = articuloRepository.findById(idArticulo).
                orElseThrow(
                        () -> new NotFoundException("No existe articulo"));

        return movimientoArticuloRepository.stockPorArticulo(articulo, fecha);
    }

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

    public MovimientoArticulo saveMovimientoSetting(DetalleAjuste detalleAjuste) {
        MovimientoArticulo movimientoArticulo = new MovimientoArticulo();
        if (detalleAjuste == null) {
            throw new NotFoundException("\nWARNING: no existen detalles por registrar en movimientos");
        }
        movimientoArticulo.setArticuloByIdArticulo(detalleAjuste.getArticuloByIdArticulo());
        movimientoArticulo.setDetalleAjusteByIdDetalleAjuste(detalleAjuste);
        movimientoArticulo.setFecha(detalleAjuste.getFecha());
        movimientoArticulo.setMovimiento(detalleAjuste.getCantidad());
        movimientoArticulo = movimientoArticuloRepository.save(movimientoArticulo);

        return movimientoArticulo;
    }

    public MovimientoArticulo saveMovimientoSales(DetalleVenta detalleVenta) {
        MovimientoArticulo movimientoArticulo = new MovimientoArticulo();
        if (detalleVenta == null) {
            throw new NotFoundException("\nWARNING: no existen detalles por registrar en movimientos");
        }
        movimientoArticulo.setArticuloByIdArticulo(detalleVenta.getArticuloByIdArticulo());
        movimientoArticulo.setDetalleVentaByIdDetalleVenta(detalleVenta);
        movimientoArticulo.setFecha(detalleVenta.getFecha());
        movimientoArticulo.setMovimiento(detalleVenta.getCantidad());

        movimientoArticulo = movimientoArticuloRepository.save(movimientoArticulo);

        return movimientoArticulo;
    }
}
