package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Pessoa;

public class PessoaRepositorio {

	private EntityManager em;

	public PessoaRepositorio(EntityManager em) {
		this.em = em;
	}
	
	public void adiciona(Pessoa pessoa) {
		em.persist(pessoa);
	}
	
	public void remove(Pessoa pessoa) {
		em.remove(pessoa);
	}
	
	public List<Pessoa> todas() {
		Query q = em.createQuery(
				"select p from Pessoa p");
		
		return q.getResultList();
	}
	
	public Pessoa pesquisaPorId(Integer id) {
		return em.find(Pessoa.class, id);
	}
	
}
