package br.com.residencia.biblioteca.dto;

public class LivroDTO {

	private Integer codigoEditora;
	private String nome;
	
	
	public LivroDTO() {		
	}

	public LivroDTO(Integer codigoEditora, String nome) {
		
		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
		
}
	

