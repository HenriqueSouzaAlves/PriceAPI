package com.price.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.price.model.Usuario;
import com.price.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findOne(id);
		
		return usuario != null ? ResponseEntity.status(HttpStatus.OK).body(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public Usuario salvar(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario usuarioSalvo = usuarioRepository.findOne(id);
		if(usuarioSalvo != null) {
			usuario.setId(id);
			usuarioRepository.save(usuario);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
