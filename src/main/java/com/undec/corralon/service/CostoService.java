package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.CostoArticulo;
import com.undec.corralon.repository.ArticuloRepository;
import com.undec.corralon.repository.CostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostoService {

    @Autowired
    CostoRepository costoRepository;
    @Autowired
    ArticuloRepository articuloRepository;

    public CostoArticulo findCostoArticulo() {
        return costoRepository.findById(1).orElseThrow(
                () ->
                        new NotFoundException("Error")
        );
    }

    public CostoArticulo findCostoForIdArticulo(Integer id) {
        Articulo articulo = articuloRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWarning: No se encontro articulo"));

        CostoArticulo costo = costoRepository.findCostoArticuloByIdArtculo(articulo);
        if (costo == null) {
            throw new NotFoundException("\nWARNING: No existe el coto para el articulo");
        }
        return costo;
    }
    public List<CostoArticulo> findAllCostoForIdArticulo(Integer id) {
        Articulo articulo = articuloRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWarning: No se encontro articulo"));

        List <CostoArticulo> costosForIdArtculo = costoRepository.findAllCostoArticuloByIdArtculo(articulo);
        if (costosForIdArtculo == null) {
            throw new NotFoundException("\nWARNING: No existe el coto para el articulo");
        }
        return costosForIdArtculo;
    }

}
