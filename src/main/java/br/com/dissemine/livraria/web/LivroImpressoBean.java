package br.com.dissemine.livraria.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.servlet.http.Part;

import br.com.dissemine.livraria.dominio.Autor;
import br.com.dissemine.livraria.dominio.produtos.Livro;
import br.com.dissemine.livraria.dominio.produtos.LivroImpresso;
import br.com.dissemine.livraria.infraestrutura.JPAUtil;

@ManagedBean
@ViewScoped
public class LivroImpressoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Livro livroImpresso = new LivroImpresso();;
	private Autor autor;
	private Part imagemCapa;
	
	public String salvar() {
		livroImpresso.adicionarAutor(autor);
		EntityManager em = JPAUtil.getEntityManager();
		String urlImagem = salvarImagemUpload(livroImpresso.getId());
		livroImpresso.setUrlImagemCapa(urlImagem);
		em.merge(livroImpresso);
		em.flush();
		return "sucesso";
	}
	
	private String salvarImagemUpload(Long id) {
		InputStream is;
	
		try {
			Path file = Files.createTempFile(Paths.get("/home/leo/desenv/servers/apache-tomcat-7.0.63/webapps/uploads"), "livroimpresso-", ".jpg");
			is = imagemCapa.getInputStream();
			if (is != null) {
				Files.copy(is, file, StandardCopyOption.REPLACE_EXISTING);
				return file.getFileName().toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Livro getLivroImpresso() {
		return livroImpresso;
	}

	public void setLivroImpresso(Livro livroImpresso) {
		this.livroImpresso = livroImpresso;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Part getImagemCapa() {
		return imagemCapa;
	}

	public void setImagemCapa(Part imagemCapa) {
		this.imagemCapa = imagemCapa;
	}
	
}
