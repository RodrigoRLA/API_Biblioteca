package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;

@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepository;

	public List<Editora> getAllEditora() {
		return editoraRepository.findAll();
	}

	public Editora getEditoraById(Integer idEditora) {
		return editoraRepository.findById(idEditora).get();
	}

	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	public EditoraDTO convertEntitytoDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		
		if (editora!=null) {
			if (editora.getCodigoEditora()==null || editora.getCodigoEditora()==0) {
				editoraDTO.setNome(editora.getNome());
			}
			else {
				editoraDTO.setNome(editora.getNome());
				editoraDTO.setCodigoEditora(editora.getCodigoEditora());
			}
			}
			return editoraDTO;
	}
	
	public Editora convertDTOtoEntity(EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		if(editoraDTO != null) {
		if (editoraDTO.getCodigoEditora()==null || editoraDTO.getCodigoEditora()==0) {
			editora.setNome(editoraDTO.getNome());
		}
		else {
			editora.setNome(editoraDTO.getNome());
			editora.setCodigoEditora(editoraDTO.getCodigoEditora());
		}
		}	
		return editora;
	}
	
	public List<EditoraDTO> getAllEditoraDTO(){
		List<Editora> listaEditora = getAllEditora();
		List<EditoraDTO> listaDTO = new ArrayList<EditoraDTO>();
		for (int i=0; i<listaEditora.size();i++) {
			listaDTO.add(convertEntitytoDTO(listaEditora.get(i)));
		}	
		return listaDTO;
	}
	
	public EditoraDTO saveEditoraDTO (EditoraDTO editoraDTO) {
		//Implementacao NOVA
		Editora editora1 = convertDTOtoEntity(editoraDTO);
		Editora registroEditora = saveEditora(editora1);
		EditoraDTO editoraFinal = convertEntitytoDTO(registroEditora);
		return editoraFinal;
	}
/*	
	  public EditoraDTO saveEditoraDTO (EditoraDTO editoraDTO) { Editora editora =
	  new Editora(); editora.setNome(editoraDTO.getNome());
	  
	  Editora novaEditora = editoraRepository.save(editora); EditoraDTO
	  novaEditoraDTO = new EditoraDTO();
	  
	  novaEditoraDTO.setCodigoEditora(novaEditora.getCodigoEditora());
	  novaEditoraDTO.setNome(novaEditora.getNome()); return novaEditoraDTO; }
	 
*/
	public Editora updateEditora(Editora editora, Integer id) {
		Editora editoraExistenteNoBanco = editoraRepository.findById(id).get();

		editoraExistenteNoBanco.setNome(editora.getNome());

		return editoraRepository.save(editoraExistenteNoBanco);
	}

	public Editora deleteEditora(Integer id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);

	}

}
