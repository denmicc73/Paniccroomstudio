package org.example.paralax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/cookies")
    public String cookiesPage() {
        return "cookies";
    }

    @GetMapping("/services-audio")
    public String servicesAudio() {
        return "services-audio";
    }

    @GetMapping("/services-video")
    public String servicesVideo() {
        return "services-video";
    }

    @GetMapping("/services-produccion")
    public String servicesProduccion() {
        return "services-produccion";
    }

    @GetMapping("/services-diseno")
    public String servicesDiseno() {
        return "services-diseno";
    }

    @GetMapping("/equipo")
    public String equipo() {
        return "equipo";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("➡️ Entrando en /login");
        return "login";
    }
}