package com.challenge_foro.challenge_foro.repositorios;

import com.challenge_foro.challenge_foro.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByCorreoElectronico(String nombreUsuraio);
}
