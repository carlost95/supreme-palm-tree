package com.undec.corralon.reportes;

import com.undec.corralon.DTO.DataReporte;
import com.undec.corralon.repository.PedidoRepository;
import com.undec.corralon.repository.RemitoRepository;
import com.undec.corralon.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReporteService {

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    RemitoRepository remitoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    public List<DataReporte> obtenerVentas(Date fechaInicial, Date fechaFinal) {
        Date dateEnd = new Date(fechaFinal.getTime() + 86400000);
        return this.ventaRepository.obtenerVentasFecha(fechaInicial, dateEnd)
                .stream()
                .map(venta -> new DataReporte(venta.get(0), venta.get(2)+"-"+venta.get(1)))
                .collect(Collectors.toList());
    }
    public List<DataReporte> obtenerRemitos(Date fechaInicial, Date fechaFinal) {
        Date dateEnd = new Date(fechaFinal.getTime() + 86400000);
        return this.remitoRepository.obtenerRemitosFecha(fechaInicial, dateEnd)
                .stream()
                .map(remito -> new DataReporte(remito.get(0), remito.get(2)+"-"+remito.get(1)))
                .collect(Collectors.toList());
    }

    public List<DataReporte> obtenerPedidos(Date fechaInicial, Date fechaFinal) {
        Date dateEnd = new Date(fechaFinal.getTime() + 86400000);
        return this.pedidoRepository.obtenerPedidosFecha(fechaInicial, dateEnd)
                .stream()
                .map(pedido -> new DataReporte(pedido.get(0), pedido.get(2)+"-"+pedido.get(1)))
                .collect(Collectors.toList());
    }

}
