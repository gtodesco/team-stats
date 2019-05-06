package br.com.teamstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.teamstats.model.ScrumMaster;
import br.com.teamstats.repository.ScrumMasterRepository;

@Service
public class ScrumMasterService {

	private ScrumMasterRepository scrumMasterRepository;
	
	public ScrumMasterService(ScrumMasterRepository scrumMasterRepository) {
		this.scrumMasterRepository = scrumMasterRepository;
	}
	
	public ScrumMaster salvar(ScrumMaster scrumMaster) {
		return scrumMasterRepository.save(scrumMaster);
	}
	
	public Optional<ScrumMaster> buscarPorID(Long id){
		return scrumMasterRepository.findById(id);
	}
	
	public void deletar(Long id) {
		scrumMasterRepository.deleteById(id);
	}
	
	public List<ScrumMaster> buscarScrumMasters(){
		return scrumMasterRepository.findAll();
	}
	
	public Optional<ScrumMaster> buscarPorEmail(String email){
		return scrumMasterRepository.findByEmailIgnoreCase(email);
	}
	
}
