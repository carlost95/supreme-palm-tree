package com.undec.corralon.service;

import com.undec.corralon.DTO.*;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<RemitoListDTO> findAllRemitos() {
        List<Remito> remitos;
        List<RemitoListDTO> remitosList;
        remitos = Optional.ofNullable(this.remitoRepository.findAll())
                .orElseThrow(() -> new NotFoundException("\nWARNING: Error no exiten remitos"));
        remitosList = Optional.ofNullable(mapperRemitoListDTO(remitos))
                .orElseThrow(() -> new NotFoundException("\nWARNING: Error al mapear remitos"));
        return remitosList;
    }


    public RemitoConsultDTO findRemitoById(Integer id) {
        RemitoConsultDTO remitoConsultDTO;
        Remito remito = this.remitoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("\nWARNING: Error no existe remito con id: " + id));
        remitoConsultDTO = mapperRemitoConsultDTO(remito);
        return remitoConsultDTO;
    }

    public List<Remito> getRemitoByStatusEntregado() {

        return Optional.ofNullable(this.remitoRepository.findAllByEntregadoEquals(true))
                .orElseThrow(() -> new NotFoundException("\nWARNING: No existe el remito " + "ENTREGADO" + " en base de datos"));
    }

    public List<Remito> getRemitoByStatusEntregadoNot() {
        return Optional.ofNullable(this.remitoRepository.findAllByEntregadoEquals(false))
                .orElseThrow(() -> new NotFoundException("\nWARNING: No existe el remito " + "NO ENTREGADO" + " en base de datos"));
    }

    public Remito saveRemito(VentaDTO ventaDTO, Venta venta) {
        Remito remitoSaved = validatorRemito(ventaDTO, venta);
        return remitoSaved;
    }

    public void saveDetalleRemito(Remito remito, VentaDTO ventaDTO) {
        mappedDetailRemito(remito, ventaDTO);
    }

    @Transactional
    public Remito changeStatusRemito(RemitoConsultDTO remitoConsultDTO) {
        Remito remito = this.remitoRepository.findById(remitoConsultDTO.getIdRemito())
                .orElseThrow(() -> new NotFoundException("\nWARNING: Error no existe remito con id: " + remitoConsultDTO.getIdRemito()));
        if (remito.getEntregado()) {
            throw new NotFoundException("\nWARNING: Error el remito ya fue entregado y no es posible cambiar el estado");
        }
        remito.setEntregado(true);
        remito = remitoRepository.save(remito);
        if (remito == null) {
            throw new NotFoundException("\nWARNING: Error al cambiar el estado del remito");
        }
        saveDetalleRemitoEnMovimiento(remito);
        return remito;
    }


    private List<RemitoListDTO> mapperRemitoListDTO(List<Remito> remitos) {
        if (remitos == null || remitos.isEmpty()) {
            throw new NotFoundException("\nWARNING: No se encontraron remitos");
        }
        List<RemitoListDTO> remitosList = new ArrayList<>();
        for (Remito remito : remitos) {
            RemitoListDTO remitoListDTO = new RemitoListDTO();
            remitoListDTO.setIdRemito(remito.getIdRemito());
            remitoListDTO.setFechaRemito(remito.getFechaRemito());
            remitoListDTO.setNroRemito(remito.getNroRemito());
            remitoListDTO.setCliente(remito.getCliente());
            remitoListDTO.setDireccion(remito.getDireccion());
            remitoListDTO.setEntregado(remito.getEntregado());
            remitosList.add(remitoListDTO);
        }
        return remitosList;
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

    private RemitoConsultDTO mapperRemitoConsultDTO(Remito remito) {
        RemitoConsultDTO remitoConsultDTO = new RemitoConsultDTO();
        if (remito == null || remito.toString().isEmpty()) {
            throw new NotFoundException("\nWARNING: No se encontro el remito");
        }
        remitoConsultDTO.setIdRemito(remito.getIdRemito());
        remitoConsultDTO.setFechaRemito(remito.getFechaRemito());
        remitoConsultDTO.setNroRemito(remito.getNroRemito());
        remitoConsultDTO.setCliente(remito.getCliente());
        remitoConsultDTO.setDireccion(remito.getDireccion());
        remitoConsultDTO.setEntregado(remito.getEntregado());
        remitoConsultDTO.setEmpresa(remito.getEmpresa());
        remitoConsultDTO.setArticulos(mapperRemitoDetails(remito));
        return remitoConsultDTO;
    }

    private List<ArticuloRemitoDTO> mapperRemitoDetails(Remito remito) {
        List<ArticuloRemitoDTO> articuloRemitoDTOList = new ArrayList<>();
        List<DetalleRemito> detalleRemitoList = detalleRemitoRepository.findByIdRemito(remito.getIdRemito());
        if (detalleRemitoList == null || detalleRemitoList.isEmpty()) {
            throw new NotFoundException("\nWARNING: No se encontro el detalle del remito");
        }
        for (DetalleRemito detalleRemito : detalleRemitoList) {
            ArticuloRemitoDTO articuloRemitoDTO = new ArticuloRemitoDTO();
            articuloRemitoDTO.setIdArticulo(detalleRemito.getArticulo().getIdArticulo());
            articuloRemitoDTO.setNombre(detalleRemito.getArticulo().getNombre());
            articuloRemitoDTO.setCodigoArticulo(detalleRemito.getArticulo().getCodigo());
            articuloRemitoDTO.setCantidad(detalleRemito.getCantidad());
            articuloRemitoDTOList.add(articuloRemitoDTO);
        }
        return articuloRemitoDTOList;
    }

    private void mappedDetailRemito(Remito remito, VentaDTO ventaDTO) {
        Articulo article;
        for (ArticuloVentaDTO detalle : ventaDTO.getArticulos()) {
            article = articuloRepository.findArticuloByCodigo(detalle.getCodigoArticulo());
            if (article == null || article.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No existe articulo con codigo: " + detalle.getCodigoArticulo());
            }
            DetalleRemito detalleRemito = new DetalleRemito();

            detalleRemito.setArticulo(article);
            detalleRemito.setRemito(remito);
            detalleRemito.setFecha(ventaDTO.getFecha());
            detalleRemito.setCantidad(detalle.getCantidad());
            detalleRemito = detalleRemitoRepository.save(detalleRemito);
            if (detalleRemito == null || detalleRemito.getIdDetalleRemito() == null) {
                throw new NotFoundException("Error al guardar el detalle del remito");
            }
        }
    }

    private void saveDetalleRemitoEnMovimiento(Remito remito) {
        List<DetalleRemito> detalleRemitoList = detalleRemitoRepository.findByIdRemito(remito.getIdRemito());

        for (DetalleRemito detalle : detalleRemitoList) {
            MovimientoArticulo movimientoArticulo = new MovimientoArticulo();
            if (detalle == null || detalle.getIdDetalleRemito() == null) {
                throw new NotFoundException("Detalle de remito vacio o null");
            }
            movimientoArticulo = this.movimientoArticuloService.saveMovimientoRemito(detalle);
            if (movimientoArticulo == null || movimientoArticulo.toString().isEmpty()) {
                throw new NotFoundException("\nWARNING: No se guardo el movimiento de articulo");
            }

        }
    }
}
