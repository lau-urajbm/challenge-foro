package com.challenge_foro.challenge_foro.controller;

import com.challenge_foro.challenge_foro.domain.model.Topico;
import com.challenge_foro.challenge_foro.domain.topico.*;
import com.challenge_foro.challenge_foro.infra.errores.ValidacionDeIntegridad;
import com.challenge_foro.challenge_foro.repositorios.TopicoRepository;
import com.challenge_foro.challenge_foro.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

//    @PostMapping
//    public ResponseEntity agregarTopico(@RequestBody @Valid DatosPublicacionTopico datosPublicacionTopico,
//                                        UriComponentsBuilder uriComponentsBuilder){
//        System.out.println(datosPublicacionTopico);
//        Topico topico = topicoRepository.save(new Topico(datosPublicacionTopico));
//        System.out.println(topico);
//        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
//        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(datosPublicacionTopico);
//        return ResponseEntity.created(url).body(datosRespuestaTopico);
//
//    }
    @PostMapping
    public ResponseEntity agregarTopico(@RequestBody @Valid DatosPublicacionTopico datosPublicacionTopico,
                                        UriComponentsBuilder uriComponentsBuilder){
        System.out.println(datosPublicacionTopico);
        var respuesta = topicoService.publicar(datosPublicacionTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10)Pageable pageable){

        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(t -> new DatosListadoTopico(t,t.getAutor().getNombre())));

    }

    @GetMapping("/{id}")
    public ResponseEntity detalleTopico(@PathVariable Long id){
        var topico = topicoRepository.findById(id);
        if(topico.isPresent()){
          //  return ResponseEntity.ok(new DatosDetalleTopico(topico.get()));
            System.out.println(new DatosDetalleTopico(topico.get(), topico.get().getAutor().getNombre()));
            return ResponseEntity.ok(new DatosDetalleTopico(topico.get(), topico.get().getAutor().getNombre()));
        }
        throw new ValidacionDeIntegridad("El id ingresado no existe");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico( @PathVariable  Long id, @RequestBody ActualizarDatosTopico actualizarDatosTopico){
        var topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            System.out.println(actualizarDatosTopico);
            var datosRespuesta = topicoService.actualizar(topico.get(), id, actualizarDatosTopico);
            System.out.println(datosRespuesta);
            return ResponseEntity.ok(datosRespuesta);
        }
        throw new ValidacionDeIntegridad("El id ingresado no existe");

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topicoRepository.delete(topico.get());
            return ResponseEntity.ok("Tópico eliminado con éxito");
        }
        throw new ValidacionDeIntegridad("El id ingresado no existe");
    }

}
