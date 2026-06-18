package org.example.paralax.service;

import org.example.paralax.model.Contacto;
import org.example.paralax.repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService {

    private final ContactoRepository repo;

    public ContactoService(ContactoRepository repo) {
        this.repo = repo;
    }

    public Contacto guardar(Contacto contacto) {
        return repo.save(contacto);
    }

    public List<Contacto> listarTodos() {
        return repo.findAll();
    }

}