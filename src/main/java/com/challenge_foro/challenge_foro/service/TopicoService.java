package com.challenge_foro.challenge_foro.service;

import com.challenge_foro.challenge_foro.domain.model.Topico;
import com.challenge_foro.challenge_foro.domain.model.Usuario;
import com.challenge_foro.challenge_foro.domain.topico.ActualizarDatosTopico;
import com.challenge_foro.challenge_foro.domain.topico.DatosPublicacionTopico;
import com.challenge_foro.challenge_foro.domain.topico.DatosRespuestaTopico;
import com.challenge_foro.challenge_foro.infra.errores.ValidacionDeIntegridad;
import com.challenge_foro.challenge_foro.repositorios.TopicoRepository;
import com.challenge_foro.challenge_foro.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    public DatosRespuestaTopico publicar(DatosPublicacionTopico datosPublicacionTopico){
        if(!usuarioRepository.findById(datosPublicacionTopico.autor_id()).isPresent()){
            throw new ValidacionDeIntegridad("El id del usuario no fue encontrado.");
        }
        if(topicoRepository.findByTitulo(datosPublicacionTopico.titulo()).isPresent()){
            throw new ValidacionDeIntegridad("El título que está intentando agregar para su tópico ya existe, intente con uno diferente");

        }
        if(topicoRepository.findByMensaje(datosPublicacionTopico.mensaje()).isPresent()){
            throw new ValidacionDeIntegridad("El mensaje que está intentando agregar para su tópico ya existe, intente con uno diferente");

        }

        Usuario usuario = usuarioRepository.findById(datosPublicacionTopico.autor_id()).get();
        var topico = new Topico(datosPublicacionTopico, usuario);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);

    }
    public DatosRespuestaTopico actualizar(Topico topico, Long id, ActualizarDatosTopico actualizarDatosTopico){
        var titulo = topicoRepository.findByTitulo(actualizarDatosTopico.titulo());
        var mensaje = topicoRepository.findByMensaje(actualizarDatosTopico.mensaje());

        if(mensaje.isPresent()){
            throw new ValidacionDeIntegridad("El mensaje que está intentando agregar para su tópico ya existe, intente con uno diferente");
        }
        else if(titulo.isPresent()){
            throw new ValidacionDeIntegridad("El título que está intentando agregar para su tópico ya existe, intente con uno diferente");

        }
        topico.actualizarTopico(actualizarDatosTopico);
        return new DatosRespuestaTopico(topico);

    }



}
