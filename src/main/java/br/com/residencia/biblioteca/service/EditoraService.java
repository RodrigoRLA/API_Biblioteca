package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepository;

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	LivroService livroService;
	
	@Autowired
	EmailService emailService;
	

	public List<Editora> getAllEditora() {
		return editoraRepository.findAll();
	}

	public List<EditoraDTO> getAllEditorasDTO() {
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();

		for (Editora editora : listaEditora) {

			EditoraDTO editoraDTO = toDTO(editora);

			listaEditoraDTO.add(editoraDTO);
		}
		return listaEditoraDTO;

	}

	public Editora getEditoraById(Integer idEditora) {
		return editoraRepository.findById(idEditora).get();
	}

	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}

	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = toEntidade(editoraDTO);
		Editora novaEditora = editoraRepository.save(editora);

		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora);

		return editoraAtualizadaDTO;
	}

	public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, Integer id) {

		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		if (editoraExistenteNoBanco != null) {

			editoraExistenteNoBanco = toEntidade(editoraDTO);
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);

			editoraAtualizadaDTO = toDTO(editoraAtualizada);

		}
		emailService.sendEmail("alveslisboa1995@gmail.com", "Teste de envio", editoraAtualizadaDTO.toString());
		
		return editoraAtualizadaDTO;
	}

	private Editora toEntidade(EditoraDTO editoraDTO) {
		Editora editora = new Editora();

		editora.setNome(editoraDTO.getNome());
		return editora;
	}

	private EditoraDTO toDTO(Editora editora) {

		EditoraDTO editoraDTO = new EditoraDTO();

		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());

		return editoraDTO;
	}

	public Editora updateEditora(Editora editora, Integer id) {

		Editora editoraExistenteNoBanco = editoraRepository.findById(id).get();

		editoraExistenteNoBanco.setNome(editora.getNome());

		return editoraRepository.save(editoraExistenteNoBanco);
	}

	public Editora deleteEditora(Integer id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);

	}
	
	public List<EditoraDTO> getAllEditorasLivrosDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		for (Editora editora : listaEditora) {
		
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livro> listaLivros = new ArrayList<>();
			List<LivroDTO> listaLivrosDTO = new ArrayList<>();
			
			listaLivros = livroRepository.findByEditora(editora);
				for(Livro livro : listaLivros) {
					LivroDTO livroDTO = livroService.toDTO(livro);
					listaLivrosDTO.add(livroDTO);
				}
				editoraDTO.setListalivrosDTO(listaLivrosDTO);

			listaEditoraDTO.add(editoraDTO);
		}
		return listaEditoraDTO;
	}
	
	
	
}
