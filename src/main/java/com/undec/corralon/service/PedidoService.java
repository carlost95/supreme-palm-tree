package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloStockDTO;
import com.undec.corralon.DTO.DetalleTipoMovimientoDTO;
import com.undec.corralon.DTO.PedidoDTO;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.DetallePedido;
import com.undec.corralon.modelo.MovimientoArticulo;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.DetallePedidoRepository;
import com.undec.corralon.repository.PedidoRepository;
import com.undec.corralon.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<Pedido> findAllOrders() {
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

    @Transactional
    public PedidoDTO saveOrder(PedidoDTO pedidoDTO) {
        Pedido pedidoTosave = new Pedido();

        pedidoTosave.setHabilitado(true);
        pedidoTosave = mappedOrder(pedidoTosave, pedidoDTO);
        pedidoTosave = pedidoRepository.save(pedidoTosave);
        if (pedidoTosave == null)
            throw new NotFoundException("\nWARNING: No se guardo el pedido");
        mappedDetailOrder(pedidoTosave, pedidoDTO);

        return pedidoDTO;
    }


    public Pedido modifyOrder(Pedido pedido) {
        if (validationOrderNull(pedido))
            throw new BadRequestException("\nError: no se admiten valores nullos en los pedidos a modificar");

        Pedido pedidoModify = this.pedidoRepository.findById(pedido.getIdPedido())
                .orElseThrow(() ->
                        new NotFoundException("\nWARING: no existe un pedido por modificar"));

        pedidoModify.setNombre(pedido.getNombre());
        pedidoModify.setDescripcion(pedido.getDescripcion());
        pedidoModify.setFecha(pedido.getFecha());

        pedidoModify = this.pedidoRepository.save(pedidoModify);
        if (pedidoModify == null)
            throw new NotFoundException("\nError al almacenar pedido no se puede modificar");

        return pedidoModify;
    }

    private boolean validationOrderNull(Pedido pedido) {
        if (pedido.getIdPedido() == null
                || pedido.getNombre() == null
                || pedido.getFecha() == null) {
            return true;
        }
        return false;
    }

    public Pedido changueHabilityOrder(Integer id) {
        if (id == null) {
            throw new BadRequestException("\nWARNING: error el identificador de ajuste no puede null");
        }
        Pedido pedido = this.pedidoRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: No existe el pedido para reagilzar el cambio de habilitacion"));

        pedido.setHabilitado(!pedido.getHabilitado());
        pedido = pedidoRepository.save(pedido);
        if (pedido == null) {
            throw new NotFoundException("\nError al malmacenar los cambios del pedido");
        }

        return pedido;
    }

    private Pedido mappedOrder(Pedido pedidoTosave, PedidoDTO pedidoDTO) {
        if (validationNullOrder(pedidoDTO)) {
            throw new BadRequestException("\nError: No se pueden cargar pedidos con nombres o fechas null");
        }
        pedidoTosave.setNombre(pedidoDTO.getNombre());
        pedidoTosave.setDescripcion(pedidoDTO.getDescripcion());
        pedidoTosave.setFecha(pedidoDTO.getFecha());

        return pedidoTosave;
    }

    private boolean validationNullOrder(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getNombre() == null || pedidoDTO.getFecha() == null)
            return true;
        return false;
    }

    private void mappedDetailOrder(Pedido pedido, PedidoDTO pedidoDTO) {
        Articulo article;
        for (ArticuloStockDTO articulo : pedidoDTO.getArticulos()) {

            MovimientoArticulo movimientoArticulo;
            DetallePedido detallePedido = new DetallePedido();
            article = articuloRepository.findArticuloByCodigo(articulo.getCodigoArt());
            if (article == null) {
                throw new NotFoundException("\nWARINNG: No existe articulo en base de datos");
            }
            detallePedido.setArticuloByIdArticulo(article);
            detallePedido.setPedidoByIdPedido(pedido);
            detallePedido.setFecha(pedidoDTO.getFecha());
            detallePedido.setCantidad(articulo.getCantidad());
            detallePedido = detallePedidoRepository.save(detallePedido);
            if (detallePedido == null) {
                throw new NotFoundException("\nWARNING: Error al almacenar el detalle del pedido");
            }
            movimientoArticulo = this.movimientoArticuloService.saveMovimientoOrder(detallePedido);
            if (movimientoArticulo == null) {
                throw new NotFoundException("\nWARNING: Error en la carga de movimientos");
            }
        }
    }
}
