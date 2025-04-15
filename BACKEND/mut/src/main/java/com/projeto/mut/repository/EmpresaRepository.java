package com.projeto.mut.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.projeto.mut.Entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	UserDetails findByLogin(String login);


}
