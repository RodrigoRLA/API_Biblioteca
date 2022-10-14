package br.com.residencia.biblioteca.dto;

public class EmprestimoDTO {

	private Integer codigoEditora;
	private String nome;
	
	
	public EmprestimoDTO() {		
	}

	public EmprestimoDTO(Integer codigoEditora, String nome) {
		
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
	

