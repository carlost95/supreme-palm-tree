package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.banco.BancoCambioEstadoException;
import com.undec.corralon.excepciones.marca.MarcaNotFoundException;
import com.undec.corralon.modelo.Marca;
import com.undec.corralon.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    public Response obtenerMarcaPorId(Integer id) throws MarcaNotFoundException {
        Response response = new Response();
        Marca marca = this.marcaRepository.findById(id).get();
        response.setCode(200);
        response.setMsg("Marca");
        response.setData(marca);
        return response;
    }

    public Response obtenerTodasLasMarcas() throws MarcaNotFoundException {
        Response response = new Response();
        List<Marca> marcas = this.marcaRepository.findAll();
        response.setCode(200);
        response.setMsg("Marcas");
        response.setData(marcas);
        return response;
    }

    public Response obtenerHabilitados() throws MarcaNotFoundException {
        Response response = new Response();
        List<Marca> marcas = this.marcaRepository.findAllByHabilitacionEquals(true);
        response.setCode(200);
        response.setMsg("Marcas Habilitadas");
        response.setData(marcas);
        return response;
    }

    public Response guardarMarca(Marca marca) throws MarcaNotFoundException {
        Response response = new Response();
        marca.setFechaCreacion(new Date());
        marca.setHabilitacion(true);
        marca = this.marcaRepository.save(marca);

        if (marca == null)
            throw new MarcaNotFoundException();

        response.setData(marca);
        response.setMsg("Marca guardada correctamente");
        response.setCode(200);

        return response;
    }

    public Response actualizarMarca(Marca marca) throws MarcaNotFoundException {
        Response response = new Response();
        Marca marcaToUpdate = marcaRepository.findById(marca.getId()).get();

        marcaToUpdate.setNombre(marca.getNombre());
        marcaToUpdate.setAbreviatura(marca.getAbreviatura());
        marcaToUpdate.setFechaModificacion(new Date());
        if (marcaToUpdate == null) {
            throw new MarcaNotFoundException();
        }
        response.setCode(200);
        response.setMsg("Marca actualizada");
        response.setData(marcaRepository.save(marcaToUpdate));
        return response;
    }

    public Response eliminarMarca(Integer id) throws MarcaNotFoundException {
        Response response = new Response();
        Marca marcaToDelete = marcaRepository.findById(id).get();

        marcaToDelete.setHabilitacion(true);
        marcaToDelete.setFechaBaja(new Date());
        if (marcaToDelete == null) {
            throw new MarcaNotFoundException();
        }
        response.setCode(200);
        response.setMsg("Baja de Marca");
        response.setData(marcaRepository.save(marcaToDelete));
        return response;
    }

    public Response cambiarHabilitacion(Integer id) throws BancoCambioEstadoException {
        Response response = new Response();

        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if (!marcaOptional.isPresent()) {
            throw new BancoCambioEstadoException();
        }
        Marca marca = marcaOptional.get();
        marca.setHabilitacion(!marca.getHabilitacion());
        marca = marcaRepository.save(marca);

        response.setCode(200);
        response.setMsg("El banco cambio de estado exitosamente");
        response.setData(marca);
        return response;
    }


}
