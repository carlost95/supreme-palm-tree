package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloVentaDTO;
import com.undec.corralon.DTO.VentaDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class RemitoService {
    @Autowired
    RemitoRepository remitoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    DireccionRepository direccionRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    DetalleRemitoRepository detalleRemitoRepository;
    @Autowired
    MovimientoArticuloService movimientoArticuloService;

    public Remito saveRemito(VentaDTO ventaDTO, Venta venta) {
        Remito remitoSaved = validatorRemito(ventaDTO, venta);
        return remitoSaved;
    }
    public void saveDetalleRemito( Remito remito, VentaDTO ventaDTO) {
       mappedDetailRemito( remito , ventaDTO);
    }

    private Remito validatorRemito(VentaDTO ventaDTO, Venta venta) {
        Cliente clienteSave = clienteRepository.findById(ventaDTO.getIdCliente()).orElseThrow(
                () -> new NotFoundException("\nWARNING: No existe cliente asociado a la venta"));
        Direccion direccionSave = direccionRepository.findById(ventaDTO.getIdDireccion()).orElseThrow(
                () -> new NotFoundException("\nWARNING: No existe direccion asociada a la venta"));
        Empresa empresaSave = empresaRepository.findById(ventaDTO.getIdEmpresa()).orElseThrow(
                () -> new NotFoundException("\nWARNING: No existe empresa asociada a la venta"));
        return mapperRemito(clienteSave, direccionSave, empresaSave, venta, ventaDTO);
    }

    private Remito mapperRemito(Cliente cliente, Direccion direccion, Empresa empresa, Venta venta, VentaDTO ventaDTO) {
        Remito remito = new Remito();
        Long nroRemito = getRemitoNumber();

        remito.setEmpresa(empresa);
        remito.setCliente(cliente);
        remito.setDireccion(direccion);
        remito.setVenta(venta);
        remito.setNroRemito(nroRemito);
        remito.setFechaRemito(ventaDTO.getFecha());
        remito.setEntregado(false);
        remito = remitoRepository.save(remito);
        if (remito == null || remito.getIdRemito() == null) {
            throw new NotFoundException("Error al guardar el remito");
        }
        return remito;
    }

    private Long getRemitoNumber() {
        Long numeroRemito = remitoRepository.count();

        if (numeroRemito == null || numeroRemito == 0) {
            numeroRemito = 1L;
        } else {
            numeroRemito = remitoRepository.findLastRemitoNumber() + 1;
        }
        return numeroRemito;
    }

    private void mappedDetailRemito(Remito remito, VentaDTO ventaDTO) {
        Articulo article;
        for (ArticuloVentaDTO detalle : ventaDTO.getArticulos()) {
            article = articuloRepository.findArticuloByCodigo(detalle.getCodigoArticulo());
            if (article == null || article.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No existe articulo con codigo: " + detalle.getCodigoArticulo());
            }
//            MovimientoArticulo movimientoArticulo;
            DetalleRemito detalleRemito = new DetalleRemito();

            detalleRemito.setArticulo(article);
            detalleRemito.setRemito(remito);
            detalleRemito.setFecha(ventaDTO.getFecha());
            detalleRemito.setCantidad(detalle.getCantidad());
            detalleRemito = detalleRemitoRepository.save(detalleRemito);
            if (detalleRemito == null || detalleRemito.getIdDetalleRemito() == null) {
                throw new NotFoundException("Error al guardar el detalle del remito");
            }
//TODO: se debe contemplar actualizar el estado de los remitos al momento de descontar
/*
           las cantidades de los articulos
            movimientoArticulo = this.movimientoArticuloService.saveMovimiento(detalleVenta);
            if (movimientoArticulo == null || movimientoArticulo.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No se guardo el movimiento de articulo");
            }
 */
        }
    }
}
