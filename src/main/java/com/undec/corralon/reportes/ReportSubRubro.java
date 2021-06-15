package com.undec.corralon.reportes;

import com.undec.corralon.DTO.FileDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.SubrubroDTO;
import com.undec.corralon.DTO_PDF.SubrubroDTOPDF;
import com.undec.corralon.modelo.Rubro;
import com.undec.corralon.modelo.SubRubro;
import com.undec.corralon.repository.RubroRepository;
import com.undec.corralon.repository.SubRubroRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportSubRubro {
    @Autowired
    CreateReportService createReportService;

    @Autowired
    SubRubroRepository subRubroRepository;

    public Response exportReport() {
        Response response = new Response();

        try {
            List<SubrubroDTOPDF> subRubrosPdf = new ArrayList<SubrubroDTOPDF>();
            List<SubRubro> subRubroList = subRubroRepository.findAllByHabilitacionEquals(true);

            for (SubRubro subRub : subRubroList) {
                SubrubroDTOPDF subRubro = new SubrubroDTOPDF();
                subRubro.setNombre(subRub.getNombre());
//                subRubro.setNombreRubro(subRub.getIdRubro());
                subRubrosPdf.add(subRubro);
            }

            String plantilla = "classpath:report/report_gen_2.jrxml";
            JRDataSource data = new JRBeanCollectionDataSource(subRubrosPdf);

            Map<String, Object> parametros = new HashMap<String , Object>();
            parametros.put("titulo", "Reporte de Sub Rubros");
            parametros.put("c1", "Nombre");
            parametros.put("c2", "Descripcion");
            parametros.put("c3", "Nombre Rubro");
            String filename = "Reporte Sub-Rubro"+" "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh mm ss"));

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
