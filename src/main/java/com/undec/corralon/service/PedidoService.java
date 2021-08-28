package com.undec.corralon.service;

import com.undec.corralon.DTO.PedidoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.pedido.PedidoErrorToDeleteException;
import com.undec.corralon.excepciones.pedido.PedidoErrorToUpdateException;
import com.undec.corralon.excepciones.pedido.PedidoException;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.repository.PedidoRepository;
import com.undec.corralon.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

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

    public PedidoDTO saveOrder(PedidoDTO pedidoDTO) {
        Pedido pedidoTosave = new Pedido();
        pedidoTosave = mappedOrder(pedidoTosave, pedidoDTO);

        return pedidoDTO;
    }



    public Response modificarPedido(Pedido pedido) throws PedidoException {
        Response response = new Response();
        Pedido pedidoToSave = this.pedidoRepository.findById(pedido.getIdPedido()).get();

        pedidoToSave.setNombre(pedido.getNombre());
        pedidoToSave.setDescripcion(pedido.getDescripcion());
        pedidoToSave.setFecha(pedido.getFecha());

        if (pedidoToSave == null)
            throw new PedidoErrorToUpdateException();

        this.pedidoRepository.save(pedidoToSave);
        response.setCode(200);
        response.setData(pedidoToSave);
        response.setMsg("Pedido actualizado");

        return response;
    }

    public Response darBajaPedido(Integer id) throws PedidoException {
        Response response = new Response();
        Pedido pedido = this.pedidoRepository.findById(id).get();

        pedido.setHabilitado(false);

        if (pedido == null)
            throw new PedidoErrorToDeleteException();

        this.pedidoRepository.save(pedido);
        response.setCode(200);
        response.setData(pedido);
        response.setMsg("Pedido dado de baja");

        return response;
    }
    private Pedido mappedOrder(Pedido pedidoTosave, PedidoDTO pedidoDTO) {
        if (validationNullOrder(pedidoDTO)) {
            throw new BadRequestException("\nError: No se pueden cargar pedidos con nombres o fechas null");
        }
        pedidoTosave.setNombre(pedidoDTO.getNombre());
        pedidoTosave.setDescripcion(pedidoDTO.getDescripcion());

        return pedidoTosave;
    }

    private boolean validationNullOrder(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getNombre() == null || pedidoDTO.getFecha() == null)
            return true;
        return false;
    }

}
