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

import br.com.teamstats.model.Pessoa;
import br.com.teamstats.service.PessoaService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PessoaController {

	private PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa){
		System.out.print("Criando uma pessoa");
		pessoa = pessoaService.salvar(pessoa);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
	}
	
	@PutMapping("/pessoas")
	public ResponseEntity<Void> alterar(@RequestBody Pessoa pessoa){
		System.out.println("Alterando pessoa");
		if(!pessoaService.buscarPorID(pessoa.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pessoaService.salvar(pessoa);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		System.out.println("Deletando pessoa");
		if(!pessoaService.buscarPorID(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pessoaService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> buscarPessoas(){
		System.out.println("Buscando pessoa");
		List<Pessoa> pessoas = pessoaService.buscarPessoas();
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);		
	}
	
	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long id){
		System.out.println("Buscando pessoa");
		Optional<Pessoa> pessoa = pessoaService.buscarPorID(id);
		if(pessoa.isPresent()) {
			return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/pessoas/buscar")
	public ResponseEntity<Optional<Pessoa>> buscarPessoaPorEmail(@PathParam(value = "email") String email){
		Optional<Pessoa> pessoa = pessoaService.buscarPorEmail(email);
		return new ResponseEntity<Optional<Pessoa>>(pessoa, HttpStatus.OK);
	}
	
}