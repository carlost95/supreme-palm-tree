package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    DepartamentoRepository departamentoRepository;

    public List<Departamento>  listAllDepartment(){
        List<Departamento> departamentos = departamentoRepository.findAll();
        if(departamentos.isEmpty())
            throw new NotFoundException("\nWARNING: No existen departamentos");

        return departamentos;
    }

    public List<Departamento> listAllDepartmentHabilitation(){
        List<Departamento> departamentos = departamentoRepository.findByHabilitadoEquals(true);
        if(departamentos.isEmpty())
            throw new NotFoundException("\nWARNING: No existen departamentos habilitados");
        return departamentos;
    }
    public Departamento findByIdDepartment(Integer id){
        Departamento departamento = departamentoRepository.findById(id).
                orElseThrow(()-> new NotFoundException("\nWARNINGH: No existe el departamento solicitado"));
        return departamento;
    }
    public Departamento saveDepartment(Departamento departamento){
        if (departamento.getNombre().isEmpty()||departamento.getAbreviatura().isEmpty()) {
            throw new BadRequestException("\nWARNING: los datos del departamento no puede ser null");
        }
        if(validationDepartment(departamento)) {
            throw new BadRequestException("\nWARNING: El departamento ingresao esta duplicado");
        }
        departamento.setHabilitado(true);
        departamento = departamentoRepository.save(departamento);

        if(departamento.toString().isEmpty())
            throw new NotFoundException("\nWARNING: Error en la carga de departamento");

        return departamento;
    }

    public Departamento updatedDepartment(Departamento departamento){
        Departamento updatedDepart = departamentoRepository.findById(departamento.getIdDepartamento()).
                orElseThrow(()->
                        new NotFoundException("\nWARNING: No existe el departamento que se quiere actualizar"));

        updatedDepart.setIdDepartamento(departamento.getIdDepartamento());
        updatedDepart.setNombre(departamento.getNombre());
        updatedDepart.setAbreviatura(departamento.getAbreviatura());
        updatedDepart.setHabilitado(departamento.getHabilitado());
        validateUdatedDepartment(updatedDepart);
        updatedDepart=departamentoRepository.save(updatedDepart);
        if (updatedDepart==null)
            throw new NotFoundException("\nWARNING: Error al actualizar el departamento no existe");

        return updatedDepart;
    }

    public Departamento changeStatus(Integer id) {
        Departamento depChange = departamentoRepository.findById(id).
                orElseThrow(()-> new NotFoundException("\nWARNING: No existe el departamento para el cambio de estado"));

        depChange.setHabilitado(!depChange.getHabilitado());
        depChange = departamentoRepository.save(depChange);
        if (depChange.toString().isEmpty())
            throw new NotFoundException("\nWARNING: error el el metodo de guardado del cambio de estado de departamento");

        return depChange;
    }
    private boolean validationDepartment(Departamento departamento) {
        return departamentoRepository.existsByNombreOrAbreviatura(departamento.getNombre(), departamento.getAbreviatura());
    }
    private void validateUdatedDepartment(Departamento departamento) {
        if (departamentoRepository.existsByNombreAndIdDepartamentoNot(departamento.getNombre(), departamento.getIdDepartamento()))
            throw new BadRequestException("\nWARNING: El nombre del departamento ya existe");
        if (departamentoRepository.existsByAbreviaturaAndIdDepartamentoNot(departamento.getAbreviatura(), departamento.getIdDepartamento()))
            throw new BadRequestException("\nWARNING: La abreviatura del departamento ya existe");
    }
}
