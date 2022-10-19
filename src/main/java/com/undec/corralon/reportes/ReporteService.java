package com.undec.corralon.reportes;

import com.undec.corralon.DTO.VentaPorMes;
import com.undec.corralon.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReporteService {

    @Autowired
    VentaRepository ventaRepository;

    public List<VentaPorMes> obtenerVentasPorMes() {
        // TODO: cambiar fecha por anio
        return this.ventaRepository.obtenerVentasPorMes(2022)
                .stream()
                .map(venta -> new VentaPorMes(venta.get(1), venta.get(0)))
                .collect(Collectors.toList());
//        return Arrays.asList(new VentaPorMes(1, 66),
//                new VentaPorMes(2, 59),
//                new VentaPorMes(3, 80),
//                new VentaPorMes(4, 81),
//                new VentaPorMes(5, 56),
//                new VentaPorMes(6, 55),
//                new VentaPorMes(7, 40));
    }
}
