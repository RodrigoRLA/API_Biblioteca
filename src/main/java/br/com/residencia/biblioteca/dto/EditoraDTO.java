package br.com.residencia.biblioteca.dto;

import java.util.List;

public class EditoraDTO {

	private Integer codigoEditora;
	private String nome;
	private String imagemNome;
	private String imagemFileName;
	private String imagemUrl;

	private List<LivroDTO> listalivrosDTO;

	public EditoraDTO() {
	}

	public EditoraDTO(Integer codigoEditora, String nome) {

		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

	public String getImagemNome() {
		return imagemNome;
	}

	public void setImagemNome(String imagemNome) {
		this.imagemNome = imagemNome;
	}

	public String getImagemFileName() {
		return imagemFileName;
	}

	public void setImagemFileName(String imagemFileName) {
		this.imagemFileName = imagemFileName;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	@Override
	public String toString() {
		return "EditoraDTO [codigoEditora=" + codigoEditora + ", nome=" + nome + ", listalivrosDTO=" + listalivrosDTO
				+ "]";
	}

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public List<LivroDTO> getListalivrosDTO() {
		return listalivrosDTO;
	}

	public void setListalivrosDTO(List<LivroDTO> listalivrosDTO) {
		this.listalivrosDTO = listalivrosDTO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
