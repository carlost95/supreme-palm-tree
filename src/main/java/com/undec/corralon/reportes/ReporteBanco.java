package com.undec.corralon.reportes;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.repository.BancoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteBanco {
    @Autowired
    BancoRepository bancoRepository;
    public Response exportReport(String format) {
        Response response = new Response();
        try {
            String path = "C:/Users/Carlos/Desktop/jasper/";

            List<Banco> bankList = bancoRepository.findAll();
            File file = ResourceUtils.getFile("classpath:bancos.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(bankList);

            Map<String, Object> parametros = new HashMap<String , Object>();
            parametros.put("Prueba Tesis", "es solo una prueba");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, ds);
            if (format.equalsIgnoreCase("html")){
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "bancos.html");
            }
            if (format.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "bancos.pdf");
            }
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
