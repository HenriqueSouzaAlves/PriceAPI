package com.price.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.price.model.Lancamento;

@Transactional
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	@Query(value = "select * from lancamento\n" + 
			"Inner join usuario on usuario.id = lancamento.usuario_id and usuario.id = ?", nativeQuery = true)
	List<Lancamento> pesquisar(Long id);
}
