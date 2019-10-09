package com.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.price.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
