package com.challenge_foro.challenge_foro.domain.topico;

import com.challenge_foro.challenge_foro.domain.model.Status;
import com.challenge_foro.challenge_foro.domain.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(String título,
                                 String mensaje,
                                 LocalDateTime fechaCreación,
                                 Status status,
                                 String autor,
                                 String curso) {
    public DatosDetalleTopico(Topico topico, String autor) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),autor, topico.getCurso());
    }
}
