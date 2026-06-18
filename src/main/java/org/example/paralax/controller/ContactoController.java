package org.example.paralax.controller;

import org.example.paralax.model.Contacto;
import org.example.paralax.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class ContactoController {

    @Autowired
    private ContactoService service;

    @GetMapping("/admin-contactos")
    public String adminContacto(Model model) {
        List<Contacto> contactos = service.listarTodos();
        int totalContactos = contactos.size();
        long emailsUnicos = contactos.stream()
                .map(Contacto::getEmail)
                .filter(Objects::nonNull)
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .count();
        int longitudMedia = contactos.stream()
                .map(Contacto::getMensaje)
                .filter(Objects::nonNull)
                .mapToInt(String::length)
                .sum();

        if (totalContactos > 0) {
            longitudMedia = longitudMedia / totalContactos;
        }

        int actividadPorcentaje = Math.min(100, totalContactos * 10);
        long emailsUnicosPorcentaje = totalContactos > 0 ? (emailsUnicos * 100 / totalContactos) : 0;
        int textoPorcentaje = Math.min(100, longitudMedia / 2);

        Contacto ultimoContacto = contactos.stream()
                .filter(contacto -> contacto.getId() != null)
                .max((a, b) -> a.getId().compareTo(b.getId()))
                .orElse(null);

        model.addAttribute("contactos", contactos);
        model.addAttribute("totalContactos", totalContactos);
        model.addAttribute("emailsUnicos", emailsUnicos);
        model.addAttribute("longitudMedia", longitudMedia);
        model.addAttribute("ultimoContacto", ultimoContacto);
        model.addAttribute("actividadPorcentaje", actividadPorcentaje);
        model.addAttribute("emailsUnicosPorcentaje", emailsUnicosPorcentaje);
        model.addAttribute("textoPorcentaje", textoPorcentaje);
        return "admin-contactos";
    }

    @GetMapping("/admin-contacto")
    public String adminContactoAlias() {
        return "redirect:/admin-contactos";
    }

    @GetMapping("/contacto")
    public String formulario(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "contacto";
    }

    @PostMapping("/contacto")
    public String guardar(@ModelAttribute Contacto contacto, Model model) {

        service.guardar(contacto);

        model.addAttribute("mensajeExito", "Mensaje enviado correctamente");

        return "resultado";
    }

}
