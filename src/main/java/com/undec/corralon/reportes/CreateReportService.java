package com.undec.corralon.reportes;
import net.sf.jasperreports.engine.*;

import java.io.FileInputStream;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Map;

@Service
public class CreateReportService {

    public byte[] createReport(JRDataSource entity, String plantilla,Map<String, Object> parametros ) throws Exception {

        try {

            File file = ResourceUtils.getFile(plantilla);
            JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, entity);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new Exception();
        }

    }

    private byte[] readContentIntoByteArray(File file)
    {
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try
        {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            for (int i = 0; i < bFile.length; i++)
            {
                System.out.print((char) bFile[i]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bFile;
    }

}
