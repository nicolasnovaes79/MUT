package com.projeto.mut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.Entity.Funcionario;
import com.projeto.mut.dto.FuncionarioDTO;
import com.projeto.mut.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvar(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = service.salvar(funcionario);
        FuncionarioDTO dto = service.converterParaDTO(novoFuncionario);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarPorEmpresa() {
        // Obter a empresa associada ao usuário autenticado
        Empresa empresa = (Empresa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<Funcionario> funcionarios = service.listarPorEmpresa(empresa.getId());
        
        List<FuncionarioDTO> listaDTO = service.converterListaParaDTO(funcionarios);
        
        return ResponseEntity.ok(listaDTO);
    }
    
    @GetMapping("/despachados")
    public ResponseEntity<List<FuncionarioDTO>> listarFuncionariosDespachados() {
        // Obter a empresa associada ao usuário autenticado
        Empresa empresa = (Empresa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<Funcionario> funcionarios = service.listarDespachadosPorEmpresa(empresa.getId());
        
        List<FuncionarioDTO> listaDTO = service.converterListaParaDTO(funcionarios);
        
        return ResponseEntity.ok(listaDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(funcionario -> ResponseEntity.ok(service.converterParaDTO(funcionario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        Funcionario atualizado = service.atualizar(funcionario);
        FuncionarioDTO dto = service.converterParaDTO(atualizado);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
