package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	
	
	@Autowired
	LivroRepository livroRepository;
	
	public List<Livro> getAllLivro(){
		return livroRepository.findAll();
	}
	
	public Livro getLivroById (Integer idLivro) {
		return livroRepository.findById(idLivro).get();
	}
	
	public Livro saveLivro (Livro livro) {
		return livroRepository.save(livro);
	}
	
	public Livro updateLivro(Livro livro, Integer id) {
		Livro livroExistenteNoBanco = livroRepository.findById(id).get();
		
		livroExistenteNoBanco.setCodigoISBN(livro.getCodigoISBN());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		//livroExistenteNoBanco.setEditora(livro.getEditora());
		//livroExistenteNoBanco.setEmprestimo(livro.getEmprestimo());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
				
		return livroRepository.save(livroExistenteNoBanco);
	}
	
	public Livro deleteLivro (Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}
	
	
	

}
