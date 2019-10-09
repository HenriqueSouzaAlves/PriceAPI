package com.price.resource;

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

import com.price.model.Lancamento;
import com.price.repository.LancamentoRepository;
import com.price.repository.LancamentoRepositoryQuery;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoRepositoryQuery lancamentoRepositoryQuery;
	
	
	@GetMapping
	public List<Lancamento> listar() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		return lancamentos;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
		Lancamento lancamento = lancamentoRepository.findOne(id);
		
		return lancamento != null ? ResponseEntity.status(HttpStatus.OK).body(lancamento) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Lancamento>> buscarPorUsuarioId(@PathVariable Long id) {
		List<Lancamento> lancamentos = lancamentoRepositoryQuery.findById(id.toString());
		
		return lancamentos != null ? ResponseEntity.status(HttpStatus.OK).body(lancamentos) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento) {
		lancamentoRepository.save(lancamento);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> atualizar(@RequestBody Lancamento lancamento, @PathVariable Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		if(lancamentoSalvo != null) {
			lancamento.setId(id);
			lancamentoRepository.save(lancamento);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.notFound().build();
		}
		lancamentoRepository.delete(id);
		return ResponseEntity.noContent().build();
	}
}
