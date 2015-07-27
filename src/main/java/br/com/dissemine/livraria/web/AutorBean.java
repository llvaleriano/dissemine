package br.com.dissemine.livraria.web;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.dissemine.livraria.dominio.Autor;
import br.com.dissemine.livraria.infraestrutura.JPAUtil;

@ManagedBean
public class AutorBean {
	
	private Autor autor = new Autor();
	private List<Autor> autores;
	private EntityManager em = JPAUtil.getEntityManager();
	
	@PostConstruct
	public void carregaAutor() {
		Map<String, String> parametros = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap();
		String parametroId = parametros.get("id");
		if (parametroId != null) {
			autor = em.find(Autor.class, Long.valueOf(parametroId));
		}
	}
	
	public String salvar() {
		if (autor.getId() == null) {
			em.persist(autor);
		} else {
			em.merge(autor);
		}
		return "listaAutores.xhtml?faces-redirect=true";
	}
	
	@SuppressWarnings("unchecked")
	public List<Autor> getAutores() {
		if (autores == null) {
			Query query = em.createQuery("select a from Autor a order by a.nome", Autor.class);
			autores = query.getResultList();
		}
		return autores;
	}
	
	public String excluir(Autor autor) {
		Autor autorGerenciado = em.merge(autor);
		em.remove(autorGerenciado);
		autores = null;
		return "sucesso";
	}

	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
