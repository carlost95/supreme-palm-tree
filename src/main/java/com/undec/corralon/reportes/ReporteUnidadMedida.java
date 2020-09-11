package com.undec.corralon.reportes;

import com.undec.corralon.DTO.FileDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.repository.UnidadMedidaRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteUnidadMedida {
    @Autowired
    CreateReportService createReportService;

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public Response exportReport() {
        Response response = new Response();

        try {
            List<UnidadMedida> unidadMedidaList = unidadMedidaRepository.findAll();
            String plantilla = "classpath:report/report_gen.jrxml";
            JRDataSource data = new JRBeanCollectionDataSource(unidadMedidaList);

            Map<String, Object> parametros = new HashMap<String , Object>();
            parametros.put("titulo", "Reporte Ud. Medida");
            parametros.put("c1", "Id");
            parametros.put("c2", "Nombre");
            parametros.put("c3", "Abreviatura");
            String filename = "Reporte Unidad Medida"+" "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh mm ss"));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setFile(createReportService.createReport(data, plantilla,parametros));
            fileDTO.setName(filename);

            response.setData(fileDTO);
            response.setCode(200);
            response.setMsg("se creo report correctamente");

        }
        catch (Exception ex){
            response.setCode(400);
            response.setMsg("error al crear:" + ex.getMessage()
            );
            ex.printStackTrace();
        }
        return response;
    }
}
