package com.projeto.mut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{login}/id")
    public ResponseEntity<Long> getIdEmpresaPorLogin(@PathVariable String login) {
        // Usando o serviço para buscar a empresa
        UserDetails empresa = empresaService.loadUserByUsername(login);
        if (empresa != null) {
            return ResponseEntity.ok(((Empresa) empresa).getId());  // Casting para Empresa
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
