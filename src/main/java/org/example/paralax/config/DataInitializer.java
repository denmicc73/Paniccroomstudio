package org.example.paralax.config;

import org.example.paralax.model.Usuario;
import org.example.paralax.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

 @Bean
 CommandLineRunner init(UsuarioRepository repo, PasswordEncoder encoder) {
  return args -> {
   Usuario admin = repo.findByUsername("admin").orElseGet(() -> {
    Usuario nuevoAdmin = new Usuario();
    nuevoAdmin.setUsername("admin");
    return nuevoAdmin;
   });

   admin.setPassword(encoder.encode("1234"));
   admin.setRole("ROLE_ADMIN");

   repo.save(admin);

   System.out.println("Usuario admin preparado");
  };
 }
}
