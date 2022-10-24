package com.undec.corralon.reportes;

import com.undec.corralon.DTO.VentaPorMes;
import com.undec.corralon.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
    }
//    public List<VentaPorMes> obtenerVentasPorMes(Date fecha) {
//        // TODO: cambiar fecha por anio
//        return this.ventaRepository.obtenerVentasPorMes(fecha)
//                .stream()
//                .map(venta -> new VentaPorMes(venta.get(1), venta.get(0)))
//                .collect(Collectors.toList());
//    }

    public List<VentaPorMes> obtenerVentas(Date fechaInicial, Date fechaFinal){
        System.out.println(fechaInicial);
        System.out.println(fechaFinal);
        Date dateEnd = new Date(fechaFinal.getTime() + 86400000);
        return this.ventaRepository.obtenerVentas(fechaInicial, dateEnd)
                .stream()
                .map(venta -> new VentaPorMes(venta.get(1), venta.get(0)))
                .collect(Collectors.toList());
    }
}
