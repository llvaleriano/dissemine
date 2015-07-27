package br.com.dissemine.livraria.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Query;

import br.com.dissemine.livraria.dominio.produtos.Livro;
import br.com.dissemine.livraria.infraestrutura.JPAUtil;

@ManagedBean
@ViewScoped
public class ListaLivrosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tituloLivro = "";
	private String ultimoTituloPesquisado = "";
	private List<Livro> livrosDisponiveis;

	@SuppressWarnings("unchecked")
	private void carregarLivrosDisponiveis() {
		Query query;
		if (tituloLivro != null && !tituloLivro.isEmpty()) {
			query = JPAUtil.getEntityManager().createNamedQuery(Livro.QUERY_LIVROS_DISPONIVEIS_POR_NOME, Livro.class);
			query.setParameter("titulo", "%" + tituloLivro.toUpperCase() + "%");
		} else {
			query = JPAUtil.getEntityManager().createNamedQuery(Livro.QUERY_LIVROS_DISPONIVEIS, Livro.class);
		}
		livrosDisponiveis = query.getResultList();
		ultimoTituloPesquisado = tituloLivro;
	}
	
	public List<Livro> getLivrosDisponiveis() {
		if (! ultimoTituloPesquisado.equals(tituloLivro)) {
			carregarLivrosDisponiveis();
		}
		return livrosDisponiveis;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}

}
