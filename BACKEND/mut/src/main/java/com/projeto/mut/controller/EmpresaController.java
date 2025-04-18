package com.projeto.mut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.dto.EmpresaDTO;
import com.projeto.mut.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{login}/id")
    public ResponseEntity<Long> getIdEmpresaPorLogin(@PathVariable String login) {
        UserDetails userDetails = empresaService.loadUserByUsername(login);
        if (userDetails != null && userDetails instanceof Empresa) {
            return ResponseEntity.ok(((Empresa) userDetails).getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaPorId(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        if (empresa != null) {
            EmpresaDTO dto = empresaService.converterParaDTO(empresa);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
