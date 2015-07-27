package br.com.dissemine.livraria.dominio.produtos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.dissemine.livraria.dominio.Autor;

@Entity
@DiscriminatorValue(value="impresso")
public class LivroImpresso extends Livro {

	private double peso;
	private double altura;
	private double largura;
	
	public LivroImpresso() {
		super();
	}
	
	public LivroImpresso(Autor autor, String titulo) {
		super(autor, titulo);
	}
	
	@Override
	public boolean concederDesconto(double percentualDesconto) {
		if (percentualDesconto < 0.3) {
			//valor = valor - (valor * percentualDesconto);
			valor -= valor * percentualDesconto;
			System.out.println("Valor com desconto - Impresso: " + valor);
			return true;
		}
		return false;
	}
	
	
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}
}
