package com.projeto.mut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.dto.AutenticacaoDTO;
import com.projeto.mut.dto.LoginRespostaDTO;
import com.projeto.mut.dto.RegistrarDTO;
import com.projeto.mut.repository.EmpresaRepository;
import com.projeto.mut.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final EmpresaRepository repository;
    private final TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, EmpresaRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data) {
        var empresaSenha = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(empresaSenha);

        var token = tokenService.gerarToken((Empresa) auth.getPrincipal());

        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistrarDTO data) {
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
            Empresa novaEmpresa = new Empresa(data, encryptedPassword, data.role());

            this.repository.save(novaEmpresa);

            return ResponseEntity.ok().build();
        }
    }
}
