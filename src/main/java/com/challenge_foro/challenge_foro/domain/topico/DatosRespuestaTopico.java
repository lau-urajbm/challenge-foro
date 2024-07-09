package com.challenge_foro.challenge_foro.domain.topico;

import com.challenge_foro.challenge_foro.domain.model.Status;
import com.challenge_foro.challenge_foro.domain.model.Topico;
import com.challenge_foro.challenge_foro.domain.model.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
//        Long autor_id,
        String curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getCurso());
    }
}
