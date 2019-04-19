package br.com.teamstats.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.teamstats.model.Sprint;
import br.com.teamstats.service.SprintService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class SprintController {

	private SprintService sprintService;

	public SprintController(SprintService sprintService) {
		this.sprintService = sprintService;
	}
	
	@PostMapping("/sprints")
	public ResponseEntity<Sprint> criar(@RequestBody Sprint sprint){
		System.out.print("Criando uma sprint");
		sprint = sprintService.salvar(sprint);
		return new ResponseEntity<Sprint>(sprint, HttpStatus.CREATED);
	}
	
	@PutMapping("/sprints")
	public ResponseEntity<Void> alterar(@RequestBody Sprint sprint){
		System.out.println("Alterando sprint");
		if(!sprintService.buscarPorID(sprint.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		sprintService.salvar(sprint);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/sprints/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		System.out.println("Deletando sprint");
		if(!sprintService.buscarPorID(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		sprintService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/sprints")
	public ResponseEntity<List<Sprint>> buscarSprints(){
		System.out.println("Buscando sprint");
		List<Sprint> sprints = sprintService.buscarSprints();
		return new ResponseEntity<List<Sprint>>(sprints, HttpStatus.OK);		
	}
	
	@GetMapping("/sprints/{id}")
	public ResponseEntity<Sprint> buscarSprint(@PathVariable Long id){
		System.out.println("Buscando sprint");
		Optional<Sprint> sprint = sprintService.buscarPorID(id);
		if(sprint.isPresent()) {
			return new ResponseEntity<Sprint>(sprint.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}