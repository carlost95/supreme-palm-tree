package com.undec.corralon.security.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.security.dto.NewUsuario;
import com.undec.corralon.security.entity.Rol;
import com.undec.corralon.security.entity.Usuario;
import com.undec.corralon.security.enums.RolNombre;
import com.undec.corralon.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolService rolService;
    @Autowired
    PasswordEncoder passwordEncoder;

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

    public void updateUser(NewUsuario newUsuario) {
        Usuario usuario = usuarioRepository.findById(newUsuario.getId()).get();
        usuario.setNombre(newUsuario.getNombre());
        usuario.setNombreUsuario(newUsuario.getNombreUsuario());
        usuario.setEmail(newUsuario.getEmail());
        usuario.setPassword(passwordEncoder.encode(newUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (newUsuario.getRoles().contains("gerente")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_GERENTE).get());
        } else if (newUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
    }

    public Response getListUserName(String nombreUsuario) {
        Response response = new Response();
        Optional<Usuario> user = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (user == null)
            throw new EntityNotFoundException();
        response.setCode(200);
        response.setMsg("usuario Encontrado");
        response.setData(user);
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
