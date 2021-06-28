package com.undec.corralon.config;

import com.undec.corralon.security.entity.Rol;
import com.undec.corralon.security.enums.RolNombre;
import com.undec.corralon.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {
    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        Rol rolGerente = new Rol(RolNombre.ROLE_GERENTE);
//solo funciona para carga de rol
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        rolService.save(rolGerente);
    }
}
