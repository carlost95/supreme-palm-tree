package com.undec.corralon.service;

import com.undec.corralon.DTO.DetalleTipoMovimientoDTO;
import com.undec.corralon.DTO.VentaDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.ClienteRepository;
import com.undec.corralon.repository.DetalleVentaRepository;
import com.undec.corralon.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    DetalleVentaRepository detalleVentaRepository;
    @Autowired
    MovimientoArticuloService movimientoArticuloService;

    public List<Venta> findAllSales() {
        return Optional.ofNullable(this.ventaRepository.findAll())
                .orElseThrow(() -> new NotFoundException("\nWARNING: Error no exiten ventas"));
    }

    public Venta findSaleById(Integer id) {
        return this.ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("\nWARNING: Error no existe venta por id"));
    }

    public VentaDTO saveSale(VentaDTO ventaDTO) {
        if (validationNullSale(ventaDTO)) {
            throw new NotFoundException("\nWARNING: Datos de venta requeridos no pueden ser null");
        }

        Venta saleToSave = mappedSale(ventaDTO)
                .map(sale -> ventaRepository.save(sale))
                .orElseThrow(() -> new NotFoundException("\nWARNING: No se guardo la venta"));

        mappedDetailSale(saleToSave, ventaDTO);

        return ventaDTO;
    }

    private Optional<Venta> mappedSale(VentaDTO ventaDTO) {

        return Optional.of(clienteRepository.findById(ventaDTO.getIdCliente())
                .map(cliente -> saleMapper(cliente, ventaDTO))
                .orElseThrow(() -> new NotFoundException("WARNING: No existe cliente asociado a la venta")));

    }

    private Venta saleMapper(Cliente cliente, VentaDTO ventaDTO) {
        Venta saleMapped = new Venta();

        saleMapped.setClienteByIdCliente(cliente);
        saleMapped.setFechaVenta(ventaDTO.getFecha());
        saleMapped.setDescuento(ventaDTO.getDescuento());
        saleMapped.setRecargo(ventaDTO.getRecargo());
        saleMapped.setTotal(ventaDTO.getTotalPagar());
        return saleMapped;
    }

    private boolean validationNullSale(VentaDTO ventaDTO) {
        return ventaDTO.getIdCliente() == null || ventaDTO.getFecha() == null;
    }

    private void mappedDetailSale(Venta venta, VentaDTO ventaDTO) {

        for (DetalleTipoMovimientoDTO detalle : ventaDTO.getDetalleVenta()) {


            Articulo article = Optional.ofNullable(articuloRepository.findArticuloForCodigo(detalle.getNombreArticulo(), detalle.getCodigoArticulo()))
                    .orElseThrow(() -> new NotFoundException("\nWARINNG: No existe articulo en base de datos"));

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setArticuloByIdArticulo(article);
            detalleVenta.setVentaByIdVenta(venta);
            detalleVenta.setFecha(ventaDTO.getFecha());
            detalleVenta.setCantidad(detalle.getValorIngresado());
            detalleVenta = Optional.ofNullable(detalleVentaRepository.save(detalleVenta))
                    .orElseThrow(() -> new NotFoundException("\nWARNING: Error al almacenar el detalle de venta"));

            Optional.ofNullable(this.movimientoArticuloService.saveMovimientoSales(detalleVenta))
                    .orElseThrow(() -> new NotFoundException("\nWARNING: Error en la carga de movimientos"));

        }
    }

}
