package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.Pedido.PedidoErrorToDeleteException;
import com.undec.corralon.excepciones.Pedido.PedidoErrorToSaveException;
import com.undec.corralon.excepciones.Pedido.PedidoErrorToUpdateException;
import com.undec.corralon.excepciones.Pedido.PedidoException;
import com.undec.corralon.modelo.Pedido;
import com.undec.corralon.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Response obtenerTodosLosPedidos(){
        Response response = new Response();
        response.setCode(200);
        response.setData(this.pedidoRepository.findAll());
        response.setMsg("Todos los pedidos");

        return response;
    }

    public Response obtenerPedidosHabilitados(){
        Response response = new Response();

        response.setCode(200);
        response.setData(this.pedidoRepository.findByHabilitacionEquals(1));
        response.setMsg("Todos los pedidos");

        return response;
    }

    public Response obtenerPedidoPorId(Integer id){
        Response response = new Response();
        Pedido pedido = this.pedidoRepository.findById(id).get();

        response.setCode(200);
        response.setData(pedido);
        response.setMsg("Pedido " + pedido.getNombre());

        return response;
    }

    public Response crearPedido(Pedido pedido) throws PedidoException {
        Response response = new Response();
        pedido.setHabilitacion(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaDeCarga = LocalDateTime.now().format(formatter).toString();
        horaDeCarga = horaDeCarga.substring(10, horaDeCarga.length());
        pedido.setFecha( pedido.getFecha() + horaDeCarga);
        pedido = this.pedidoRepository.save(pedido);

        if(pedido == null)
            throw new PedidoErrorToSaveException();

        response.setCode(200);
        response.setData(pedido);
        response.setMsg("Pedido guardado");
        return response;
    }

    public Response modificarPedido(Pedido pedido) throws PedidoException {
        Response response = new Response();
        Pedido pedidoToSave = this.pedidoRepository.findById(pedido.getId()).get();

        pedidoToSave.setNombre(pedido.getNombre());
        pedidoToSave.setDescripcion(pedido.getDescripcion());
        pedidoToSave.setFecha(pedido.getFecha());

        if(pedidoToSave == null)
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

        pedido.setHabilitacion(0);

        if(pedido == null)
            throw new PedidoErrorToDeleteException();

        this.pedidoRepository.save(pedido);
        response.setCode(200);
        response.setData(pedido);
        response.setMsg("Pedido dado de baja");

        return response;
    }

}
