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

import br.com.teamstats.model.Equipe;
import br.com.teamstats.service.EquipeService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class EquipeController {

	private EquipeService equipeService;

	public EquipeController(EquipeService equipeService) {
		this.equipeService = equipeService;
	}
	
	@PostMapping("/equipes")
	public ResponseEntity<Equipe> criar(@RequestBody Equipe equipe){
		System.out.print("Criando uma equipe");
		equipe = equipeService.salvar(equipe);
		return new ResponseEntity<Equipe>(equipe, HttpStatus.CREATED);
	}
	
	@PutMapping("/equipes")
	public ResponseEntity<Void> alterar(@RequestBody Equipe equipe){
		System.out.println("Alterando equipe");
		if(!equipeService.buscarPorID(equipe.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		equipeService.salvar(equipe);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/equipes/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		System.out.println("Deletando equipe");
		if(!equipeService.buscarPorID(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		equipeService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/equipes")
	public ResponseEntity<List<Equipe>> buscarEquipes(){
		System.out.println("Buscando equipe");
		List<Equipe> equipes = equipeService.buscarEquipes();
		return new ResponseEntity<List<Equipe>>(equipes, HttpStatus.OK);		
	}
	
	@GetMapping("/equipes/{id}")
	public ResponseEntity<Equipe> buscarEquipe(@PathVariable Long id){
		System.out.println("Buscando equipe");
		Optional<Equipe> equipe = equipeService.buscarPorID(id);
		if(equipe.isPresent()) {
			return new ResponseEntity<Equipe>(equipe.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}