package br.com.teamstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.teamstats.model.Pessoa;
import br.com.teamstats.repository.PessoaRepository;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Optional<Pessoa> buscarPorID(Long id){
		return pessoaRepository.findById(id);
	}
	
	public void deletar(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	public List<Pessoa> buscarPessoas(){
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> buscarPorEmail(String email){
		return pessoaRepository.findByEmailIgnoreCase(email);
	}
	
}
