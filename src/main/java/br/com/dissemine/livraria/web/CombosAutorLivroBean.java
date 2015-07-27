package br.com.dissemine.livraria.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.dissemine.livraria.dominio.Autor;
import br.com.dissemine.livraria.dominio.produtos.Livro;
import br.com.dissemine.livraria.infraestrutura.JPAUtil;

@ManagedBean
//@ViewScoped
public class CombosAutorLivroBean {

	private Autor autor;
	private Livro livroSelecionado;
	private List<Livro> livros;

	@SuppressWarnings("unchecked")
	public void buscarLivrosPorAutor() {
		if (autor != null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createNamedQuery(Livro.QUERY_LIVROS_POR_AUTOR, Livro.class);
			query.setParameter("idAutor", autor.getId());
			livros = query.getResultList();
		}
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	public List<Livro> getLivros() {
		buscarLivrosPorAutor();
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
}
