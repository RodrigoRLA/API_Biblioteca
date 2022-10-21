package br.com.residencia.biblioteca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.service.AlunoService;


@RestController
@RequestMapping("/alunos")
public class AlunoController {
	@Autowired
	AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAllAlunos(){
		return new ResponseEntity<>(alunoService.getAllAlunos(),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		if(null != aluno)
			return new ResponseEntity<>(aluno,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(aluno,
					HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> saveAluno(@RequestBody Aluno aluno) {
		return new ResponseEntity<>(alunoService.saveAluno(aluno),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno(@Valid @RequestBody Aluno aluno, 
			@PathVariable Integer id){
		return new ResponseEntity<>(alunoService.updateAluno(aluno, id),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> deleteAluno(@PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.deleteAluno(id),
				HttpStatus.OK);
	}

	//DTO
	
	@GetMapping("/dto")
	public ResponseEntity<List<AlunoDTO>> getAllAlunosDTO(){
		return new ResponseEntity<>(alunoService.getAllAlunosDTO(),
				HttpStatus.OK);
	}

	
	@PostMapping("/dto")
	public ResponseEntity<AlunoDTO> saveEditoraDTO(@RequestBody AlunoDTO alunoDTO) {
		return new ResponseEntity<>(alunoService.saveAlunoDTO(alunoDTO),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/dto/{id}")
    public ResponseEntity<AlunoDTO> updateAlunoDTO (@RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {
        return new ResponseEntity<>(alunoService.updateAlunoDTO(alunoDTO,id),HttpStatus.OK);
    }
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<AlunoDTO> getAlunoDTOById(@PathVariable Integer id) {
		AlunoDTO alunoDTO = alunoService.getAlunoByIdDTO(id);
		if(null != alunoDTO)
			return new ResponseEntity<>(alunoDTO,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(alunoDTO,
					HttpStatus.NOT_FOUND);
	}
	
	///AlunoResumoDTO
	/*
	@GetMapping("/AlunoResumoDTO/{id}")
	public ResponseEntity<AlunoResumoDTO> getAlunoResumoDTOById(@PathVariable Integer id) {
		AlunoResumoDTO alunoResumoDTO = alunoService.getAlunoByIdDTO(id);
		if(null != alunoResumoDTO)
			return new ResponseEntity<>(alunoResumoDTO,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(alunoResumoDTO,
					HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/AlunoResumoDTO")
	public ResponseEntity<List<AlunoResumoDTO>> getAllAlunoResumoDTO(){
		return new ResponseEntity<>(alunoService.getAllAlunoResumoDTO(),
				HttpStatus.OK);
	}
	*/
}