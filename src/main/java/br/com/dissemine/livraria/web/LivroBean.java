package br.com.dissemine.livraria.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.dissemine.livraria.dominio.Autor;
import br.com.dissemine.livraria.dominio.produtos.Livro;
import br.com.dissemine.livraria.dominio.produtos.LivroDigital;
import br.com.dissemine.livraria.dominio.produtos.LivroImpresso;
import br.com.dissemine.livraria.dominio.produtos.MiniLivro;
import br.com.dissemine.livraria.infraestrutura.JPAUtil;

@ManagedBean
public class LivroBean {

	private EntityManager em = JPAUtil.getEntityManager();
	
	private List<Autor> autoresSelecionados = new ArrayList<>();
	
	private Livro livroImpresso = new LivroImpresso();
	private Livro livroDigital = new LivroDigital();
	private Livro miniLivro = new MiniLivro();
		
	private Double valorLivroDigital;
	private Double valorLivroImpresso;
	private Double valorMiniLivro;
	
	public String salvar() {
		if (valorLivroDigital != null) {
			criarLivroDigital();
			em.persist(livroDigital);
		}
		if (valorMiniLivro != null) {
			criarMiniLivro();
			em.persist(miniLivro);
		}
		if (valorLivroImpresso!= null) {
			livroImpresso = em.getReference(Livro.class, livroImpresso.getId());
			em.persist(livroImpresso);
		}
		return "sucesso";
	}

	private void criarLivroDigital() {
		livroDigital.setTitulo(livroImpresso.getTitulo());
		livroDigital.setDescricao(livroImpresso.getDescricao());
		livroDigital.setIsbn(livroImpresso.getIsbn());
		livroDigital.setNumeroPaginas(livroImpresso.getNumeroPaginas());
		livroDigital.setEditora(livroImpresso.getEditora());
		livroDigital.setValor(valorLivroDigital);
		livroDigital.setAutores(livroImpresso.getAutores());
	}
	
	private void criarMiniLivro() {
		miniLivro.setTitulo(livroImpresso.getTitulo());
		miniLivro.setDescricao(livroImpresso.getDescricao());
		miniLivro.setIsbn(livroImpresso.getIsbn());
		miniLivro.setNumeroPaginas(livroImpresso.getNumeroPaginas());
		miniLivro.setEditora(livroImpresso.getEditora());
		miniLivro.setValor(valorLivroDigital);
		miniLivro.setAutores(livroImpresso.getAutores());
	}

	public Livro getLivroImpresso() {
		return livroImpresso;
	}

	public void setLivroImpresso(Livro livroImpresso) {
		this.livroImpresso = livroImpresso;
	}

	public Livro getLivroDigital() {
		return livroDigital;
	}

	public void setLivroDigital(Livro livroDigital) {
		this.livroDigital = livroDigital;
	}

	public Livro getMiniLivro() {
		return miniLivro;
	}

	public void setMiniLivro(Livro miniLivro) {
		this.miniLivro = miniLivro;
	}

	public Double getValorLivroDigital() {
		return valorLivroDigital;
	}

	public void setValorLivroDigital(Double valorLivroDigital) {
		this.valorLivroDigital = valorLivroDigital;
	}

	public Double getValorLivroImpresso() {
		return valorLivroImpresso;
	}

	public void setValorLivroImpresso(Double valorLivroImpresso) {
		this.valorLivroImpresso = valorLivroImpresso;
	}

	public Double getValorMiniLivro() {
		return valorMiniLivro;
	}

	public void setValorMiniLivro(Double valorMiniLivro) {
		this.valorMiniLivro = valorMiniLivro;
	}

	public List<Autor> getAutoresSelecionados() {
		return autoresSelecionados;
	}

	public void setAutoresSelecionados(List<Autor> autoresSelecionados) {
		this.autoresSelecionados = autoresSelecionados;
	}
	
}
