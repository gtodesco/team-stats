package br.com.teamstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.teamstats.model.Equipe;
import br.com.teamstats.repository.EquipeRepository;

@Service
public class EquipeService {

	private EquipeRepository equipeRepository;
	
	public EquipeService(EquipeRepository equipeRepository) {
		this.equipeRepository = equipeRepository;
	}
	
	public Equipe salvar(Equipe equipe) {
		return equipeRepository.save(equipe);
	}
	
	public Optional<Equipe> buscarPorID(Long id){
		return equipeRepository.findById(id);
	}
	
	public void deletar(Long id) {
		equipeRepository.deleteById(id);
	}
	
	public List<Equipe> buscarEquipes(){
		return equipeRepository.findAll();
	}
	
}
