package com.undec.corralon.service;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.articulo.ArticuloErrorToDeleteException;
import com.undec.corralon.excepciones.articulo.ArticuloErrorToSaveException;
import com.undec.corralon.excepciones.articulo.ArticuloErrorToUpdateException;
import com.undec.corralon.excepciones.articulo.ArticuloException;
import com.undec.corralon.excepciones.tipoDireccion.TipoDireccionListNotFoundException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.SubRubro;
import com.undec.corralon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    RubroRepository rubroRepository;

    @Autowired
    SubRubroRepository subRubroRepository;

    @Autowired
    FormaDePagoRepository formaDePagoRepository;

    public Response obtenerTodosLosArticulos(){
        Response response = new Response();
        List<Articulo> articulos = articuloRepository.findAll();

        response.setCode(200);
        response.setMsg("Todos los articulos: ");
        response.setData(articulos);
        return response;
    }

    public Response obtenerTodosLosArticulosHabilitados(){
        Response response = new Response();
        List<Articulo> articulos = articuloRepository.findArticuloByHabilitacionEquals(1);

        response.setCode(200);
        response.setMsg("Todos los articulos habilitados: ");
        response.setData(articulos);
        return response;
    }

    public Response obtenerArticuloPorId(Integer id){
        Response response = new Response();
        Articulo articulo = articuloRepository.findById(id).get();

        response.setCode(200);
        response.setMsg("Articulo: " + id);
        response.setData(articulo);
        return response;
    }

    public Response crearArticulo(ArticuloDTO articuloDTO) throws ArticuloException {
        Response response = new Response();
        Articulo articulo = dtoToEntity(articuloDTO);


        if(articulo == null)
            throw new ArticuloErrorToSaveException();

        articulo.setFechaCreacion(new Date());
        articulo.setHabilitacion(1);
        articulo = articuloRepository.save(articulo);

        response.setCode(200);
        response.setMsg("Creado");
        response.setData(articulo);

        return response;
    }

    public Response actualizarArticulo(ArticuloDTO articuloDTO) throws ArticuloException {
        Response response = new Response();
        Articulo articulo = articuloRepository.findById(articuloDTO.getId()).get();

        mapperDtoEntity(articuloDTO, articulo);

        if(articulo == null)
            throw new ArticuloErrorToUpdateException();

        articulo.setFechaModificacion(new Date());
        articulo = articuloRepository.save(articulo);

        response.setCode(200);
        response.setMsg("Actualizado");
        response.setData(articulo);

        return response;
    }

    public Response darBajaArticulo(Integer id) throws ArticuloErrorToDeleteException {
        Response response = new Response();
        Articulo articulo = articuloRepository.findById(id).get();

        if(articulo == null)
            throw new ArticuloErrorToDeleteException();

        articulo.setFechaBaja(new Date());
        articulo.setHabilitacion(0);
        articulo = articuloRepository.save(articulo);

        response.setCode(200);
        response.setMsg("Articulo dado de baja");
        response.setData(articulo);

        return response;
    }

    private Articulo dtoToEntity(ArticuloDTO articuloDTO) {
        Articulo articulo = new Articulo();
        mapperDtoEntity(articuloDTO, articulo);
//        articulo.setHabilitacion(articuloDTO.getHabilitacion());
        return articulo;
    }

    private void mapperDtoEntity(ArticuloDTO articuloDTO, Articulo articulo) {
        articulo.setNombre(articuloDTO.getNombre());
        articulo.setAbreviatura(articuloDTO.getAbreviatura());
        articulo.setCodigoArt(articuloDTO.getCodigoArt());
        articulo.setStockMin(articuloDTO.getStockMin());
        articulo.setStockMax(articuloDTO.getStockMax());
        if(articuloDTO.getProveedorId() != null)
            articulo.setProveedorId(proveedorRepository.findById(articuloDTO.getProveedorId()).get());

        if(articuloDTO.getUnidadMedidaId() != null)
            articulo.setUnidadMedidaId(unidadMedidaRepository.findById(articuloDTO.getUnidadMedidaId()).get());

        if(articuloDTO.getMarcaId() != null)
            articulo.setMarcaId(marcaRepository.findById(articuloDTO.getMarcaId()).get());

        if(articuloDTO.getRubroId() != null)
            articulo.setRubroId(rubroRepository.findById(articuloDTO.getRubroId()).get());

       if(articuloDTO.getSubRubroId() != null)
            articulo.setSubRubroId(subRubroRepository.findById(articuloDTO.getSubRubroId()).get());

    }

}
