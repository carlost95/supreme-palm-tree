package com.undec.corralon.security.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.usuario.UserErrorToUpdateException;
import com.undec.corralon.excepciones.usuario.UsuarioException;
import com.undec.corralon.security.entity.Usuario;
import com.undec.corralon.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Response updateUser(Usuario usuario) throws UsuarioException {
        Response response = new Response();
        Usuario usuarioToUpdate = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).get();
        if (usuarioToUpdate == null) {
            throw new UserErrorToUpdateException("UserErrorToUpdateException");
        }
        usuarioToUpdate.setNombre(usuario.getNombre());
        usuarioToUpdate.setNombreUsuario(usuario.getNombreUsuario());
        usuarioToUpdate.setEmail(usuario.getNombreUsuario());
        usuarioToUpdate.setPassword(usuario.getPassword());
        usuarioToUpdate.setRoles(usuario.getRoles());
        response.setCode(200);
        response.setMsg("actualizacion de ususario correcta");
        response.setData(usuarioRepository.save(usuarioToUpdate));
        return response;
    }

    public Response getListAll() {
        Response response = new Response();
        List<Usuario> users = usuarioRepository.findAll();

        if (users == null)
            throw new EntityNotFoundException();

        response.setCode(200);
        response.setMsg("List users");
        response.setData(users);

        return response;
    }
}
