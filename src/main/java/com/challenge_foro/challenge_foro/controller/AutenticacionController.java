package com.challenge_foro.challenge_foro.controller;

import com.challenge_foro.challenge_foro.domain.model.Usuario;
import com.challenge_foro.challenge_foro.domain.usuario.DatosAutenticacionUsuario;
import com.challenge_foro.challenge_foro.infra.security.DatosJWTToken;
import com.challenge_foro.challenge_foro.infra.security.TokenService;
import com.challenge_foro.challenge_foro.repositorios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        System.out.println("en el controlador");
        System.out.println(usuarioRepository.findByCorreoElectronico(datosAutenticacionUsuario.correoElectronico()).getNombre());
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correoElectronico(),
                datosAutenticacionUsuario.contraseña());
        System.out.println(authToken);
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        System.out.println("despues de authenticationManager"+ usuarioAutenticado);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }


}
