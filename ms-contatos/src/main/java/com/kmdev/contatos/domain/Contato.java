package com.kmdev.contatos.domain;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public class Contato {

	public String id = "";
	
	public String nome = "";
	
	public String email = "";
	
	public String telefone;
	
	public String grupoId = "";
	
	public Contato() {
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getTelefone() { return telefone; }
	public void setTelefone(String telefone) { this.telefone = telefone; }

	public String getGrupoId() { return grupoId; }
	public void setGrupoId(String grupoId) { this.grupoId = grupoId; }
	
	public String toString() {
		return String.format("Id: %s, Nome: %s, E-mail: %s", id, nome, email);
	}
}
