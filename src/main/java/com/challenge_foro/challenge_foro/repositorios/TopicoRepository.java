package com.challenge_foro.challenge_foro.repositorios;

import com.challenge_foro.challenge_foro.domain.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    Optional<Topico> findByTitulo(String titulo);

    Optional<Topico> findByMensaje(String mensaje);

}
