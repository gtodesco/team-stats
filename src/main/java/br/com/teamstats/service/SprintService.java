package br.com.teamstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.teamstats.model.Sprint;
import br.com.teamstats.repository.SprintRepository;

@Service
public class SprintService {

	private SprintRepository sprintRepository;
	
	public SprintService(SprintRepository sprintRepository) {
		this.sprintRepository = sprintRepository;
	}
	
	public Sprint salvar(Sprint sprint) {
		return sprintRepository.save(sprint);
	}
	
	public Optional<Sprint> buscarPorID(Long id){
		return sprintRepository.findById(id);
	}
	
	public void deletar(Long id) {
		sprintRepository.deleteById(id);
	}
	
	public List<Sprint> buscarSprints(){
		return sprintRepository.findAll();
	}
	
}
