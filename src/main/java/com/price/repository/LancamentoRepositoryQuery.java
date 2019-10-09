package com.price.repository;
import java.util.List;
import com.price.model.Lancamento;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> findById(String id);
	
	
}
