package com.undec.corralon.service;

import com.undec.corralon.DTO.DetallePedidoDTO;
import com.undec.corralon.DTO.PedidoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.Util;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.pedido.PedidoErrorToDeleteException;
import com.undec.corralon.excepciones.pedido.PedidoErrorToUpdateException;
import com.undec.corralon.excepciones.pedido.PedidoException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.DetallePedido;
import com.undec.corralon.modelo.MovimientoArticulo;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.DetallePedidoRepository;
import com.undec.corralon.repository.PedidoRepository;
import com.undec.corralon.repository.ProveedorRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    MovimientoArticuloService movimientoArticuloService;

    public List<Pedido> findAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        if (pedidos == null) {
            throw new NotFoundException("\nWARNING: No se existen pedidos en base de datos");
        }
        return pedidos;
    }

    public List<Pedido> findOrdersHabilitation() {
        List<Pedido> ordersHabilitation = this.pedidoRepository.findByHabilitadoEquals(true);
        if (ordersHabilitation == null) {
            throw new NotFoundException("\nWARNING: No existen pedidos habilitados");
        }
        return ordersHabilitation;
    }

    public Pedido findOrderForId(Integer id) {
        Pedido pedido = this.pedidoRepository.findById(id).
                orElseThrow(()
                        -> new NotFoundException("\nWARNING: No existe el pedido " + id + " en base de datos"));
        return pedido;
    }

    public PedidoDTO saveOrder(PedidoDTO pedidoDTO) throws ParseException {
        Pedido pedidoTosave = new Pedido();

        pedidoTosave.setHabilitado(true);
        pedidoTosave = mappedOrder(pedidoTosave, pedidoDTO);
        pedidoTosave = pedidoRepository.save(pedidoTosave);
        if (pedidoTosave == null)
            throw new NotFoundException("\nWARNING: No se guardo el pedido");
        mappedDetailOrder(pedidoTosave, pedidoDTO);

        return pedidoDTO;
    }


//    public Response modificarPedido(Pedido pedido) throws PedidoException {
//        Response response = new Response();
//        Pedido pedidoToSave = this.pedidoRepository.findById(pedido.getIdPedido()).get();
//
//        pedidoToSave.setNombre(pedido.getNombre());
//        pedidoToSave.setDescripcion(pedido.getDescripcion());
//        pedidoToSave.setFecha(pedido.getFecha());
//
//        if (pedidoToSave == null)
//            throw new PedidoErrorToUpdateException();
//
//        this.pedidoRepository.save(pedidoToSave);
//        response.setCode(200);
//        response.setData(pedidoToSave);
//        response.setMsg("Pedido actualizado");
//
//        return response;
//    }
//
//    public Response darBajaPedido(Integer id) throws PedidoException {
//        Response response = new Response();
//        Pedido pedido = this.pedidoRepository.findById(id).get();
//
//        pedido.setHabilitado(false);
//
//        if (pedido == null)
//            throw new PedidoErrorToDeleteException();
//
//        this.pedidoRepository.save(pedido);
//        response.setCode(200);
//        response.setData(pedido);
//        response.setMsg("Pedido dado de baja");
//
//        return response;
//    }

    private Pedido mappedOrder(Pedido pedidoTosave, PedidoDTO pedidoDTO) throws ParseException {
//        String[] inputDate = pedidoDTO.getFecha().split(" ");
//        LocalDate date = LocalDate.of(inputDate[0]);
//        LocalTime time = LocalTime.of(inputDate[1]);
//        LocalDateTime fecha = LocalDateTime.of(date, time);
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime fecha = LocalDateTime.parse(dateFormat.format(pedidoDTO.getFecha()));
//        Date date = dateFormat.format(pedidoDTO.getFecha());
        Date fecha = Util.stringToDate(pedidoDTO.getFecha());
        if (validationNullOrder(pedidoDTO)) {
            throw new BadRequestException("\nError: No se pueden cargar pedidos con nombres o fechas null");
        }
        pedidoTosave.setNombre(pedidoDTO.getNombre());
        pedidoTosave.setDescripcion(pedidoDTO.getDescripcion());
        pedidoTosave.setFecha(fecha);

        return pedidoTosave;
    }

    private boolean validationNullOrder(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getNombre() == null || pedidoDTO.getFecha() == null)
            return true;
        return false;
    }

    private void mappedDetailOrder(Pedido pedido, PedidoDTO pedidoDTO) throws ParseException {
        Articulo article;
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime fecha = LocalDateTime.parse(dateFormat.format(pedidoDTO.getFecha()));
        Date fecha = Util.stringToDate(pedidoDTO.getFecha());

        for (DetallePedidoDTO detalle : pedidoDTO.getDetallesPedido()) {

            MovimientoArticulo movimientoArticulo;
            DetallePedido detallePedido = new DetallePedido();
            article = articuloRepository.findArticuloForCodigo(detalle.getNombreArticulo(), detalle.getCodigoArticulo());
            if (article == null) {
                throw new NotFoundException("\nWARINNG: No existe articulo en base de datos");
            }
            detallePedido.setArticuloByIdArticulo(article);
            detallePedido.setPedidoByIdPedido(pedido);
            detallePedido.setFecha(fecha);
            detallePedido.setCantidad(detalle.getValorIngresado());
            detallePedido = detallePedidoRepository.save(detallePedido);
            if (detallePedido == null) {
                throw new NotFoundException("\nWARNING: Error al almacenar el detalle del pedido");
            }
            movimientoArticulo = this.movimientoArticuloService.saveMovimientoOrder(detallePedido);
            if (movimientoArticulo == null)
                throw new NotFoundException("\nWARNING: Error en la carga de movimientos");
        }
    }
}
