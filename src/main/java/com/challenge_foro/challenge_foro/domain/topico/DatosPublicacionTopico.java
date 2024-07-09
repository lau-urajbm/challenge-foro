package com.challenge_foro.challenge_foro.domain.topico;

import com.challenge_foro.challenge_foro.domain.model.Status;
import com.challenge_foro.challenge_foro.domain.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosPublicacionTopico(
        Long id,
        @NotNull String titulo,
        @NotNull String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        @NotNull Long autor_id,
        String curso
) {
}
