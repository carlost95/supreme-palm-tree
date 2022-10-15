package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Marca;
import com.undec.corralon.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    public List<Marca> listMark() {
        List<Marca> marcas = this.marcaRepository.findAll();
        if (marcas.isEmpty())
            throw new NotFoundException("\nWARNING: No existen marcas");
        return marcas;
    }

    public List<Marca> listMarkHabilitation() {
        List<Marca> marcas = this.marcaRepository.findAllByHabilitadoEquals(true);
        if (marcas.isEmpty())
            throw new NotFoundException("\nWARNING: No existen marcas habilitadas");
        return marcas;
    }

    public Marca findById(Integer id) {
        Marca marca = this.marcaRepository.findById(id).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe la marca en la BD"));
        return marca;
    }

    public Marca saveMark(Marca marca) {
        marca.setHabilitado(true);
        validateMark(marca);
        marca = this.marcaRepository.save(marca);

        if (marca.toString().isEmpty())
            throw new NotFoundException("\nWARNING: error al guardar marca");

        return marca;
    }

    public Marca updatedMark(Marca marca) {
        Marca marcaToUpdate = marcaRepository.findById(marca.getIdMarca()).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe la marca para actualizar"));

        marcaToUpdate.setNombre(marca.getNombre());
        marcaToUpdate.setAbreviatura(marca.getAbreviatura());
        marcaToUpdate.setHabilitado(marca.getHabilitado());
        marcaToUpdate.setIdMarca(marca.getIdMarca());
        validateUpdateMark(marcaToUpdate);

        marcaToUpdate = marcaRepository.save(marcaToUpdate);
        if (marcaToUpdate.toString().isEmpty())
            throw new NotFoundException("\nWARNING: Error al actualizar marca no se cargaron los datos");
        return marcaToUpdate;
    }

    public Marca changeHabilitation(Integer id) {

        Marca marcaChange = marcaRepository.findById(id).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe la marca que se quiere cambiar es estado de habilitacion"));

        marcaChange.setHabilitado(!marcaChange.getHabilitado());

        marcaChange = marcaRepository.save(marcaChange);
        if (marcaChange.toString().isEmpty())
            throw new NotFoundException("\nWARNING: error en el cambio de habilitacion de marca");
        return marcaChange;
    }

    private void validateMark(Marca mark) {
        validateField(mark.getNombre());
        validateField(mark.getAbreviatura());
        if (marcaRepository.existsMarcaByNombreOrAbreviatura(mark.getNombre(), mark.getAbreviatura()))
            throw new NotFoundException("\nWARNING: Nombre " +
                    mark.getNombre() + " o abreviatura " +
                    mark.getAbreviatura() + " ya existe en otra marca");
    }

    private void validateUpdateMark(Marca mark) {
        validateField(mark.getNombre());
        validateField(mark.getAbreviatura());
        if (marcaRepository.existsMarcaByNombreAndIdMarcaNot(mark.getNombre(), mark.getIdMarca()))
            throw new NotFoundException("\nWARNING: Nombre " +
                    mark.getNombre() + " ya existe en otra marca");
        if (marcaRepository.existsMarcaByAbreviaturaAndIdMarcaNot(mark.getAbreviatura(), mark.getIdMarca()))
            throw new NotFoundException("\nWARNING: Abreviatura " +
                    mark.getAbreviatura() + " ya existe en otra marca");
    }

    private void validateField(String data) {
        if (data.isEmpty()) {
            throw new BadRequestException("El campo no puede estar vacio");
        }
    }
}
