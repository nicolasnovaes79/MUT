package com.projeto.mut.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//Configuracao.java (modelo de dados)
@Entity
public class Configuracao {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private boolean nome;
 private boolean cpf;
 private boolean email;
 private boolean telefone;
 private boolean cargo;
 private boolean salario;
 private boolean dataAdmissao;
 private boolean ativo;

 public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public boolean isNome() {
	return nome;
}
public void setNome(boolean nome) {
	this.nome = nome;
}
public boolean isCpf() {
	return cpf;
}
public void setCpf(boolean cpf) {
	this.cpf = cpf;
}
public boolean isEmail() {
	return email;
}
public void setEmail(boolean email) {
	this.email = email;
}
public boolean isTelefone() {
	return telefone;
}
public void setTelefone(boolean telefone) {
	this.telefone = telefone;
}
public boolean isCargo() {
	return cargo;
}
public void setCargo(boolean cargo) {
	this.cargo = cargo;
}
public boolean isSalario() {
	return salario;
}
public void setSalario(boolean salario) {
	this.salario = salario;
}
public boolean isDataAdmissao() {
	return dataAdmissao;
}
public void setDataAdmissao(boolean dataAdmissao) {
	this.dataAdmissao = dataAdmissao;
}
public boolean isAtivo() {
	return ativo;
}
public void setAtivo(boolean ativo) {
	this.ativo = ativo;
}
 // Getters e Setters
}

