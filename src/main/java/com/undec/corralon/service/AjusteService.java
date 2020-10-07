package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToSaveException;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToUpdateException;
import com.undec.corralon.excepciones.Ajuste.AjusteErrorToUpdateHabilitacion;
import com.undec.corralon.excepciones.Ajuste.AjusteException;
import com.undec.corralon.excepciones.Pedido.PedidoErrorToSaveException;
import com.undec.corralon.modelo.Ajustes;
import com.undec.corralon.repository.AjusteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AjusteService {
    @Autowired
    AjusteRepository ajusteRepository;

    public Response obtenerTodosLosAjustes(){
        Response response = new Response();
        List<Ajustes> ajustes = this.ajusteRepository.findAll();

        response.setCode(200);
        response.setMsg("Todos los ajustes: ");
        response.setData(ajustes);
        return response;
    }
    public Response obtenerTodosLosAjustesHabilitados(){
        Response response = new Response();
        List<Ajustes> ajustesHabilitados = this.ajusteRepository.findAjustesByHabilitacionEquals(true);
        response.setCode(200);
        response.setMsg("Ajustes habilitados: ");
        response.setData(ajustesHabilitados);
        return response;
    }
    public Response obtenerAjustePorId(Integer id){
        Response response = new Response();
        Ajustes ajusteSelected = this.ajusteRepository.findById(id).get();

        response.setCode(200);
        response.setMsg(ajusteSelected.getNombre() + " seleccionado");
        response.setData(ajusteSelected);
        return response;
    }
    public Response saveAjuste(Ajustes ajuste) throws AjusteException {
        Response response = new Response();
        Ajustes ajusteToSave = new Ajustes();
        ajuste.setHabilitacion(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaDeCarga = LocalDateTime.now().format(formatter).toString();
        horaDeCarga = horaDeCarga.substring(10, horaDeCarga.length());
        ajuste.setFecha(ajuste.getFecha() + horaDeCarga);
        ajusteToSave = this.ajusteRepository.save(ajuste);

        if (ajusteToSave == null) {
            throw new AjusteErrorToSaveException("error al cargar ajuste");
        }

        response.setCode(200);
        response.setData(ajusteToSave);
        response.setMsg("ajuste guardado");
        return response;
    }
    public Response modificarAjuste (Ajustes ajuste) throws AjusteException{
        Response response = new Response();
        Ajustes ajusteToSave = this.ajusteRepository.findById(ajuste.getId()).get();
//        Pedido pedidoToSave = this.pedidoRepository.findById(pedido.getId()).get();

        ajusteToSave.setNombre(ajuste.getNombre());
        ajusteToSave.setDescripcion(ajuste.getDescripcion());
        ajusteToSave.setFecha(ajuste.getFecha());

        if (ajusteToSave == null)
            throw new AjusteErrorToUpdateException("error al actualizar el ajuste ");

        this.ajusteRepository.save(ajusteToSave);
        response.setCode(200);
        response.setData(ajusteToSave);
        response.setMsg("Pedido actualizado");

        return response;

    }
    public Response cambiarHabilitacionAjuste (Integer id) throws AjusteException{
        Response response = new Response();
        Ajustes ajusteOptional= ajusteRepository.findById(id).get();

        if (ajusteOptional==null){
            throw new AjusteErrorToUpdateHabilitacion("error the update habilitacion");
        }

        ajusteOptional.setHabilitacion(!ajusteOptional.getHabilitacion());
        ajusteOptional = ajusteRepository.save(ajusteOptional);
        response.setCode(200);
        response.setMsg("se cambio habilitacion de ajuste");
        response.setData(ajusteOptional);

        return response;
    }

}
