package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloVentaDTO;
import com.undec.corralon.DTO.VentaDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpresaRepository empresaRepository;
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

    @Transactional
    public VentaDTO saveSale(VentaDTO ventaDTO) {
        validationNullSale(ventaDTO);
        Venta saleToSave = mappedSale(ventaDTO);
        saleToSave = ventaRepository.save(saleToSave);

        if (saleToSave == null || saleToSave.getIdVenta() == null) {
            throw new NotFoundException("\nWARNING: Error al guardar venta");
        }

        mappedDetailSale(saleToSave, ventaDTO);

        return ventaDTO;
    }

    private Venta mappedSale(VentaDTO ventaDTO) {
        Venta venta;
        Cliente clienteSave = clienteRepository.findById(ventaDTO.getIdCliente()).orElseThrow(
                () -> new NotFoundException("\nWARNING: No existe cliente asociado a la venta"));
        Empresa empresaSave = empresaRepository.findById(ventaDTO.getIdEmpresa()).orElseThrow(
                () -> new NotFoundException("\nWARNING: No existe empresa asociada a la venta"));

        venta = saleMapper(clienteSave, empresaSave, ventaDTO);
        return venta;
    }

    private Venta saleMapper(Cliente cliente, Empresa empresa, VentaDTO ventaDTO) {
        Venta saleMapped = new Venta();
        Long numeroVenta = getSaleNumber();

        saleMapped.setIdCliente(cliente);
        saleMapped.setIdEmpresa(empresa);
        saleMapped.setNroVenta(numeroVenta);
        saleMapped.setFechaVenta(ventaDTO.getFecha());
        saleMapped.setDescuento(ventaDTO.getDescuento());
        saleMapped.setTotal(ventaDTO.getTotal());
        return saleMapped;
    }

    private Long getSaleNumber() {
        Long numeroVenta = ventaRepository.count();

        if (numeroVenta == null || numeroVenta == 0) {
            numeroVenta = 1L;
        } else {
            numeroVenta = ventaRepository.findLastSaleNumber() + 1;
        }
        return numeroVenta;
    }

    private void validationNullSale(VentaDTO ventaDTO) {
        if (ventaDTO.getIdCliente() == null || ventaDTO.getIdEmpresa() == null)
            throw new NotFoundException("\nWARNING: Datos de venta requeridos no pueden ser null");
    }

    private void mappedDetailSale(Venta venta, VentaDTO ventaDTO) {
        Articulo article;
        for (ArticuloVentaDTO detalle : ventaDTO.getArticulos()) {
            article = articuloRepository.findArticuloByCodigo(detalle.getCodigoArticulo());
            if (article == null || article.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No existe articulo con codigo: " + detalle.getCodigoArticulo());
            }
            MovimientoArticulo movimientoArticulo;
            DetalleVenta detalleVenta = new DetalleVenta();

            detalleVenta.setIdArticulo(article);
            detalleVenta.setIdVenta(venta);
            detalleVenta.setFecha(ventaDTO.getFecha());
            detalleVenta.setCantidad(detalle.getCantidad());
            detalleVenta = detalleVentaRepository.save(detalleVenta);
            if (detalleVenta == null || detalleVenta.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No se guardo el detalle de venta");
            }
            movimientoArticulo = this.movimientoArticuloService.saveMovimientoSales(detalleVenta);
            if (movimientoArticulo == null || movimientoArticulo.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No se guardo el movimiento de articulo");
            }
        }
    }

}
