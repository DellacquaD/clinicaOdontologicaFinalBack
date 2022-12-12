package com.example.ddellacquaFinal.security;

import com.example.ddellacquaFinal.entity.Usuario;
import com.example.ddellacquaFinal.entity.UsuarioRole;
import com.example.ddellacquaFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosUsuario implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargarDatosUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        BCryptPasswordEncoder cifradorAdmin = new BCryptPasswordEncoder();

        String passCifrada = cifrador.encode("1234");
        String passCifradaAdmin = cifradorAdmin.encode("47728891");

        Usuario usuario = new Usuario("Rodo", "Baspi", "baspi@gmail.com", passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        Usuario usuarioAdmin = new Usuario("Damian", "DellAcqua", "d.dellacquaruiz@gmail.com", passCifradaAdmin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }
}