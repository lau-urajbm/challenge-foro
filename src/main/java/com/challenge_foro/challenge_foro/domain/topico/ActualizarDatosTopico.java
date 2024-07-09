package com.challenge_foro.challenge_foro.domain.topico;

import com.challenge_foro.challenge_foro.domain.model.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ActualizarDatosTopico(
         String titulo,
         String mensaje
) {
}
