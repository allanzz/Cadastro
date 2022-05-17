package br.com.allan.dao;

import java.util.Comparator;
import java.util.List;

import br.com.allan.modelos.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Dao {
	EntityManagerFactory emf;
	EntityManager manager;

	public Dao() {
		emf = Persistence.createEntityManagerFactory("projeto");
		manager = emf.createEntityManager();
	}

	public boolean salvar(Object objeto) {
		try {
			manager.getTransaction().begin();
			manager.persist(objeto);
			manager.getTransaction().commit();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		fechar();
		return true;

	}

	public List<Produto> buscarTodos(Class classe) {
		String nome = classe.getName();
		String[] nomes = nome.split("\\.");
		nome=nomes[nomes.length - 1];

		Query query = manager.createQuery("SELECT o from "+nome+" o");
		List<Produto> produtos = (List<Produto>) query.getResultList();	
		Comparator<Produto> comparator = new Comparator<Produto>() {
		    public int compare(Produto a,Produto b) {
		        return a.getNome().compareTo(b.getNome());
		    }
		};
		produtos.sort(comparator);
		return produtos;
	}
	private List<Object> apagarTudo(Object objeto) {
		String nome = objeto.getClass().getName();
		String[] nomes = nome.split("\\.");
		nome=nomes[nomes.length - 1];
		Query query = manager.createQuery("SELECT o from "+nome+" o");
		List<Object> objetos = (List<Object>) query.getResultList();
		for (Object objeto2 : objetos) {
			System.out.println(objeto2);
			manager.getTransaction().begin();
			manager.remove(objeto2);
			manager.getTransaction().commit();
		}
		fechar();
		return objetos;
	}

	public void fechar() {
		manager.close();
		emf.close();
	}

	public void atualizar(Object objeto) {
		try {
			manager.getTransaction().begin();
			manager.merge(objeto);
			manager.getTransaction().commit();
			fechar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean remove(Object objeto) {
		try {
			if (!manager.contains(objeto)) {
			    objeto = manager.merge(objeto);
			}
			manager.getTransaction().begin();
			manager.remove(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		fechar();
		return true;
	}

	public Object buscar(Class classe,int id) {
		Object objeto= manager.find(classe, id);
		fechar();
		return objeto;
		
	}

}
