package com.projeto.mut.enums;

public enum EmpresaRole {

 CLIENTE("cliente");
	
 private String role;
 
 EmpresaRole(String role){
	 this.role = role;
 }
 
 public String getRole() {
	 return role;
 }
}
