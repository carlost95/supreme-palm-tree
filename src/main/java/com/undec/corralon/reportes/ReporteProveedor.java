package com.undec.corralon.reportes;

import com.undec.corralon.DTO.FileDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Marca;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.ProveedorRepository;
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
public class ReporteProveedor {
    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    CreateReportService createReportService;

    public Response exportReport() {
        Response response = new Response();

        try {
            List<Proveedor> proveedorList = proveedorRepository.findAll();
            String plantilla = "classpath:report/report_gen_5c.jrxml";
            JRDataSource data = new JRBeanCollectionDataSource(proveedorList);

            Map<String, Object> parametros = new HashMap<String , Object>();
            parametros.put("titulo", "Reporte de Proveedores");
            parametros.put("subtitulo", "Lista de Proveedores");
            parametros.put("c1", "Razon Social");
            parametros.put("c2", "Domicilio");
            parametros.put("c3", "Mail");
            parametros.put("c4", "Telefono");
            parametros.put("c5", "Celular");

            String filename = "Reporte Proveedor"+" "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh mm ss"));

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
