package com.kmdev.grupos.domain;

/**
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public class Grupo {

	public String id = "";
	
	public String nome = "";
	
	public Grupo() { }
	
	public Grupo(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
}
