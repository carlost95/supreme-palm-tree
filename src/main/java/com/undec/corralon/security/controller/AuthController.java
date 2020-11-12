package com.undec.corralon.security.controller;

import com.undec.corralon.DTO.Mensaje;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.security.dto.JwtDto;
import com.undec.corralon.security.dto.LoginUsuario;
import com.undec.corralon.security.dto.NewUsuario;
import com.undec.corralon.security.entity.Rol;
import com.undec.corralon.security.entity.Usuario;
import com.undec.corralon.security.enums.RolNombre;
import com.undec.corralon.security.jwt.JwtProvider;
import com.undec.corralon.security.service.RolService;
import com.undec.corralon.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<Response> listAll() throws Exception {
        Response response = usuarioService.getListAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{nombreUsuario}")
    public ResponseEntity<Response> listUserName(@PathVariable("nombreUsuario") String nombreUsuario) throws Exception {
        Response response = usuarioService.getListUserName(nombreUsuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody NewUsuario newUsuario) {
        usuarioService.updateUser(newUsuario);
        return new ResponseEntity<>(new Mensaje("usuario actualizado"), HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUsuario newUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);

        if (usuarioService.existsByNombreUsuario(newUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);

        if (usuarioService.existsByEmail(newUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);

        Usuario usuario =
                new Usuario(newUsuario.getNombre(), newUsuario.getNombreUsuario(), newUsuario.getEmail(),
                        passwordEncoder.encode(newUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (newUsuario.getRoles().contains("gerente")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_GERENTE).get());
        } else {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_GERENTE).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
