package com.undec.corralon.reportes;

import com.undec.corralon.DTO.FileDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Rubro;
import com.undec.corralon.repository.RubroRepository;
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
public class ReporteRubro {
    @Autowired
    CreateReportService createReportService;

    @Autowired
    RubroRepository rubroRepository;
    public Response exportReport() {
        Response response = new Response();

        try {
            List<Rubro> rubrosList = rubroRepository.findAll();
            String plantilla = "classpath:report/report_gen1.jrxml";
            JRDataSource data = new JRBeanCollectionDataSource(rubrosList);

            Map<String, Object> parametros = new HashMap<String , Object>();
            parametros.put("titulo", "Reporte de Rubros");
            parametros.put("subtitulo", "Reporte de Rubros");
            parametros.put("c1", "Id");
            parametros.put("c2", "Nombre");
            parametros.put("c3", "Descripcion");
            String filename = "Reporte Rubro"+" "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh mm ss"));

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
