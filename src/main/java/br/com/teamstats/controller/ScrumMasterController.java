package br.com.teamstats.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamstats.model.ScrumMaster;
import br.com.teamstats.service.ScrumMasterService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ScrumMasterController {

	private ScrumMasterService scrumMasterService;

	public ScrumMasterController(ScrumMasterService scrumMasterService) {
		this.scrumMasterService = scrumMasterService;
	}
	
	@PostMapping("/scrumMasters")
	public ResponseEntity<ScrumMaster> criar(@RequestBody ScrumMaster scrumMaster){
		System.out.print("Criando um scrumMaster");
		scrumMaster = scrumMasterService.salvar(scrumMaster);
		return new ResponseEntity<ScrumMaster>(scrumMaster, HttpStatus.CREATED);
	}
	
	@PutMapping("/scrumMasters")
	public ResponseEntity<Void> alterar(@RequestBody ScrumMaster scrumMaster){
		System.out.println("Alterando scrumMaster");
		if(!scrumMasterService.buscarPorID(scrumMaster.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		scrumMasterService.salvar(scrumMaster);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/scrumMasters/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		System.out.println("Deletando scrumMaster");
		if(!scrumMasterService.buscarPorID(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		scrumMasterService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/scrumMasters")
	public ResponseEntity<List<ScrumMaster>> buscarScrumMasters(){
		System.out.println("Buscando scrumMaster");
		List<ScrumMaster> scrumMasters = scrumMasterService.buscarScrumMasters();
		return new ResponseEntity<List<ScrumMaster>>(scrumMasters, HttpStatus.OK);		
	}
	
	@GetMapping("/scrumMasters/{id}")
	public ResponseEntity<ScrumMaster> buscarScrumMaster(@PathVariable Long id){
		System.out.println("Buscando scrumMaster");
		Optional<ScrumMaster> scrumMaster = scrumMasterService.buscarPorID(id);
		if(scrumMaster.isPresent()) {
			return new ResponseEntity<ScrumMaster>(scrumMaster.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}