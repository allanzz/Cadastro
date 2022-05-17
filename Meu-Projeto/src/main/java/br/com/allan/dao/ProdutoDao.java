package br.com.allan.dao;

import java.util.List;

import br.com.allan.modelos.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class ProdutoDao {
	EntityManagerFactory emf;
	EntityManager manager;
	public ProdutoDao() {
		emf = Persistence.createEntityManagerFactory("projeto");
		manager = emf.createEntityManager();
	}
	public void salvar(Produto produto) {
		manager.getTransaction().begin();
		manager.persist(produto);
		manager.getTransaction().commit();
		Query query = manager.createQuery("SELECT p from Produto p");
		List<Produto> produtos = (List<Produto>) query.getResultList();
		for (Produto produto2 : produtos) {
			System.out.println(produto2);
		}
		this.fechar();
		
	}
	public void fechar() {
		manager.close();
		emf.close();
	}
	

}
