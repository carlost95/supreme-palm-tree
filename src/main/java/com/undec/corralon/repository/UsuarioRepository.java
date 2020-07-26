package com.undec.corralon.repository;

import com.undec.corralon.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findUserByNombreUsuario(String nombreUsuario);
    Usuario findUserByEmail(String email);

}
