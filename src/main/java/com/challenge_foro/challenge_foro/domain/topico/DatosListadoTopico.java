package com.challenge_foro.challenge_foro.domain.topico;

import com.challenge_foro.challenge_foro.domain.model.Status;
import com.challenge_foro.challenge_foro.domain.model.Topico;
import com.challenge_foro.challenge_foro.domain.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        String título,
        String mensaje,
        LocalDateTime fechaCreación,
        Status status,
        String autor,
        String curso
) {
//    public DatosListadoTopico(Topico topico) {
////        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),topico.getAutor(), topico.getCurso());
//        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getCurso());
//    }

    public DatosListadoTopico(Topico topico, String autor) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),autor, topico.getCurso());
    }
}
