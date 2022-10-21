package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;

	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();
	}

	public Aluno getAlunoById(Integer id) {

		return alunoRepository.findById(id).orElse(null);
	}

	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public Aluno updateAluno(Aluno aluno, Integer id) {
		Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();

		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());

		return alunoRepository.save(alunoExistenteNoBanco);
	}

	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}

	// DTO
	
	public AlunoDTO getAlunoByIdDTO(Integer id) {
		AlunoDTO alunoDTO = new AlunoDTO();
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		
		alunoDTO = toDTO(aluno);
				
		return alunoDTO;
	}
	
	public List<AlunoDTO> getAllAlunosDTO() {
		List<Aluno> listaAluno = alunoRepository.findAll();
		List<AlunoDTO> listaAlunoDTO = new ArrayList<>();

		for (Aluno aluno : listaAluno) {

			AlunoDTO alunoDTO = toDTO(aluno);

			listaAlunoDTO.add(alunoDTO);
		}
		return listaAlunoDTO;

	}

	public AlunoDTO saveAlunoDTO(AlunoDTO AlunoDTO) {
		Aluno aluno = toEntidade(AlunoDTO);
		Aluno novoAluno = alunoRepository.save(aluno);

		AlunoDTO alunoAtualizadoDTO = toDTO(novoAluno);

		return alunoAtualizadoDTO;
	}

	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {

		Aluno alunoExistenteNoBanco = getAlunoById(id);
		AlunoDTO alunoAtualizadoDTO = new AlunoDTO();
		if (alunoExistenteNoBanco != null) {

			alunoExistenteNoBanco = toEntidade(alunoDTO);
			Aluno alunoAtualizado = alunoRepository.save(alunoExistenteNoBanco);

			alunoAtualizadoDTO = toDTO(alunoAtualizado);

		}
		return alunoAtualizadoDTO;
	}

	private Aluno toEntidade(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();

		aluno.setBairro(alunoDTO.getBairro());
		aluno.setCidade(alunoDTO.getCidade());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNome(alunoDTO.getNome());
		aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());

		return aluno;
	}

	private AlunoDTO toDTO(Aluno aluno) {

		AlunoDTO alunoDTO = new AlunoDTO();

		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());

		return alunoDTO;
	}
	
	/*//validação do cpf
	 public boolean isCPF(String cpf){
	        
	        if (this.cpf.equals("00000000000") || 
	            this.cpf.equals("11111111111") || 
	            this.cpf.equals("22222222222") || 
	            this.cpf.equals("33333333333") ||
	            this.cpf.equals("44444444444") ||
	            this.cpf.equals("55555555555") ||
	            this.cpf.equals("66666666666") ||
	            this.cpf.equals("77777777777") ||
	            this.cpf.equals("88888888888") ||
	            this.cpf.equals("99999999999") ||
	            this.cpf.length() != 11)
	            return(false);
	        
	        char dig10, dig11; 
	        int sm, i, r, num, peso; 

		        try { 
		            // Calculo do primeiro Digito Verificador 
		            sm = 0; 
		            peso = 10; 
		            for (i=0; i<9; i++) { 
		                num = (int)(this.cpf.charAt(i) - 48); 
		                sm = sm + (num * peso); 
		                peso = peso - 1;
		            } 
		            r = 11 - (sm % 11); 
		            if ((r == 10) || (r == 11)) 
		                dig10 = '0'; 
		            else 
		                dig10 = (char)(r + 48); 

		            // Calculo do segundo Digito Verificador 
		            sm = 0; 
		            peso = 11; 
		            for(i=0; i<10; i++) { 
		                num = (int)(this.cpf.charAt(i) - 48);
		                sm = sm + (num * peso); 
		                peso = peso - 1;
		            } 
		            r = 11 - (sm % 11); 
		            if ((r == 10) || (r == 11)) 
		                dig11 = '0'; 
		            else 
		                dig11 = (char)(r + 48); 

		            if ((dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10))) 
		                return(true); 
		            else return(false);
		        } catch(Exception e) { 
		            return(false); 
		        } 
		    }
	 */
	///AlunoResumoDTO
	/*
	public AlunoDTO getAlunoDTOById(Integer id) {
		AlunoDTO alunoDTO = new AlunoDTO();
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		
		alunoDTO = toDTO(aluno);
		
		List<Emprestimo> listaEmprestimos = emprestimoRepository.findbyAlunoDTO(aluno);
				
		return alunoDTO;
	}
	
	public List<AlunoResumoDTO> getAllAlunoResumoDTO() {
		List<Aluno> listaAluno = alunoRepository.findAll();
		List<AlunoResumoDTO> listaAlunoDTO = new ArrayList<>();

		for (Aluno aluno : listaAluno) {

			AlunoDTO alunoDTO = toDTO(aluno);

			listaAlunoDTO.add(alunoDTO);
		}
		return listaAlunoDTO;

	}
	*/
}
