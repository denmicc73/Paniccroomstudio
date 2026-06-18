package org.example.paralax.service;

import org.example.paralax.model.Usuario;
import org.example.paralax.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Optional<Usuario> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public Usuario save(Usuario usuario) {
        return repo.save(usuario);

    }
}