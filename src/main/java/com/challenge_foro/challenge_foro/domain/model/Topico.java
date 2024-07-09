package com.challenge_foro.challenge_foro.domain.model;

import com.challenge_foro.challenge_foro.domain.topico.ActualizarDatosTopico;
import com.challenge_foro.challenge_foro.domain.topico.DatosPublicacionTopico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private String curso;

    public Topico() {
    }

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Status status, Usuario autor, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

    public Topico(DatosPublicacionTopico datosPublicacionTopico, Usuario usuario) {
        this.titulo = datosPublicacionTopico.titulo();
        this.mensaje = datosPublicacionTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;
        this.autor = usuario;
        this.curso = "Desarrolla una api rest en Java";
    }

    public void actualizarTopico(ActualizarDatosTopico actualizarDatosTopico){
        if(actualizarDatosTopico.titulo() != null){
        this.titulo = actualizarDatosTopico.titulo();
        }
        if(actualizarDatosTopico.mensaje() != null) {
            this.mensaje = actualizarDatosTopico.mensaje();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
