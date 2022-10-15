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
	
	public List<EditoraDTO> getAllEditoraDTO() {
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		return ;
		
		//1. percorrer a lista de entidades Editora(chamada listaEditora)
		//2. Na lista de entidade, pegar cada entidade existente nela
		//3. Transformar cada entidade existente na lista em um DTO
		//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
		//5. Retornar/devolver a lista de DTO preenchida
		
		//OBS. para converter a entidade no DTO, usar o metodo toDTO
	}

	public Editora getEditoraById(Integer idEditora) {
		return editoraRepository.findById(idEditora).get();
	}

	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}

	/*
	 * public EditoraDTO convertEntitytoDTO(Editora editora) { EditoraDTO editoraDTO
	 * = new EditoraDTO();
	 * 
	 * if (editora!=null) { if (editora.getCodigoEditora()==null ||
	 * editora.getCodigoEditora()==0) { editoraDTO.setNome(editora.getNome()); }
	 * else { editoraDTO.setNome(editora.getNome());
	 * editoraDTO.setCodigoEditora(editora.getCodigoEditora()); } } return
	 * editoraDTO; }
	 * 
	 * public Editora convertDTOtoEntity(EditoraDTO editoraDTO) { Editora editora =
	 * new Editora();
	 * 
	 * if(editoraDTO != null) { if (editoraDTO.getCodigoEditora()==null ||
	 * editoraDTO.getCodigoEditora()==0) { editora.setNome(editoraDTO.getNome()); }
	 * else { editora.setNome(editoraDTO.getNome());
	 * editora.setCodigoEditora(editoraDTO.getCodigoEditora()); } } return editora;
	 * }
	 * 
	 * public List<EditoraDTO> getAllEditoraDTO(){ List<Editora> listaEditora =
	 * getAllEditora(); List<EditoraDTO> listaDTO = new ArrayList<EditoraDTO>(); for
	 * (int i=0; i<listaEditora.size();i++) {
	 * listaDTO.add(convertEntitytoDTO(listaEditora.get(i))); } return listaDTO; }
	 * 
	 * public EditoraDTO saveEditoraDTO (EditoraDTO editoraDTO) { //Implementacao
	 * NOVA Editora editora1 = convertDTOtoEntity(editoraDTO); Editora
	 * registroEditora = saveEditora(editora1); EditoraDTO editoraFinal =
	 * convertEntitytoDTO(registroEditora); return editoraFinal; }
	 */
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
			//editoraExistenteNoBanco.setNome(editoraDTO.getNome());
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);

			//editoraAtualizadaDTO.setCodigoEditora(editoraAtualizada.getCodigoEditora());
			//editoraAtualizadaDTO.setNome(editoraAtualizada.getNome());
		}
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

}
