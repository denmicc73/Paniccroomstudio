package org.example.paralax.service;

import org.example.paralax.model.Usuario;
import org.example.paralax.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("🔐 LOGIN INTENTO: " + username);

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ USUARIO NO ENCONTRADO: " + username);
                    return new UsernameNotFoundException("Usuario no encontrado: " + username);
                });

        System.out.println("✅ USUARIO ENCONTRADO");
        System.out.println("   username: " + usuario.getUsername());
        System.out.println("   password (BD): " + usuario.getPassword());
        System.out.println("   role: " + usuario.getRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(new SimpleGrantedAuthority(usuario.getRole()))
                .build();
    }
}