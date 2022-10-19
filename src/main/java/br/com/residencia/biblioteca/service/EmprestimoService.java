package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

	@Autowired
	EmprestimoRepository emprestimoRepository;

	public List<Emprestimo> getAllEmprestimos() {
		return emprestimoRepository.findAll();
	}

	public Emprestimo getEmprestimoById(Integer id) {
		return emprestimoRepository.findById(id).get();

	}

	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}

	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {

		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);

		emprestimoExistenteNoBanco.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());

		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());

		return emprestimoRepository.save(emprestimoExistenteNoBanco);

	}

	public Emprestimo deleteEmprestimo(Integer id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}

	// DTO

	public List<EmprestimoDTO> getAllEmprestimoDTO() {
		List<Emprestimo> listaEmprestimo = emprestimoRepository.findAll();
		List<EmprestimoDTO> listaEmprestimoDTO = new ArrayList<>();

		for (Emprestimo emprestimo : listaEmprestimo) {

			EmprestimoDTO emprestimoDTO = toDTO(emprestimo);

			listaEmprestimoDTO.add(emprestimoDTO);
		}
		return listaEmprestimoDTO;

	}

	public EmprestimoDTO saveEmprestimoDTO(EmprestimoDTO EmprestimoDTO) {
		Emprestimo emprestimo = toEntidade(EmprestimoDTO);
		Emprestimo novoEmprestimo = emprestimoRepository.save(emprestimo);

		EmprestimoDTO emprestimoAtualizadoDTO = toDTO(novoEmprestimo);

		return emprestimoAtualizadoDTO;
	}

	public EmprestimoDTO updateEmprestimoDTO(EmprestimoDTO emprestimoDTO, Integer id) {

		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		EmprestimoDTO emprestimoAtualizadoDTO = new EmprestimoDTO();
		if (emprestimoExistenteNoBanco != null) {

			emprestimoExistenteNoBanco = toEntidade(emprestimoDTO);
			Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistenteNoBanco);

			emprestimoAtualizadoDTO = toDTO(emprestimoAtualizado);
		}
		return emprestimoAtualizadoDTO;
	}

	private Emprestimo toEntidade(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo();

		emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
		emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());

		return emprestimo;
	}

	private EmprestimoDTO toDTO(Emprestimo emprestimo) {

		EmprestimoDTO emprestimoDTO = new EmprestimoDTO();

		emprestimoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoDTO.setValorEmprestimo(emprestimo.getValorEmprestimo());

		return emprestimoDTO;
	}
	//
	
}