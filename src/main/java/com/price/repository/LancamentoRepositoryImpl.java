package com.price.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.price.model.Lancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	

	@PersistenceContext
	private EntityManager manager;

	
	
	@Override
	public List<Lancamento> findById(String id) {
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

			CriteriaQuery<Lancamento> criteriaQuery = criteriaBuilder.createQuery(Lancamento.class);

			Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.like(root.get("usuario_id").get("id"), id));
			
			criteriaQuery.select(root);
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			TypedQuery<Lancamento> typedQuery = manager.createQuery(criteriaQuery);

			return typedQuery.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
