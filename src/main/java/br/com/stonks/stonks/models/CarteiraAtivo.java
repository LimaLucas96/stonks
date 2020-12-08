package br.com.stonks.stonks.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CarteiraAtivo {

    private int id;

    @NotNull
    private Carteira carteira;
    
    @NotNull
    private Ativo ativo;

    private double valor;
    
    private int quantidade;

	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dataTransacao;

    private Operacao operacao;

    public CarteiraAtivo(
    		@NotNull(message = "Carteira é obrigatoria.") Carteira carteira,
    		@NotNull(message = "Ativo é obrigatorio.") Ativo ativo,
    		@NotNull(message = "Valor é obrigatorio.") double valor,
    		@NotNull(message = "Quantidade é obrigatoria.") int quantidade,
    		@NotNull(message = "Data da compra é obrigatória.") Date data_compra,
			@NotNull(message = "Data da compra é obrigatória.") Operacao operacao) {
    	this.ativo = ativo;
    	this.carteira = carteira;
    	this.valor = valor;
    	this.quantidade = quantidade;
        this.dataTransacao = data_compra;
        this.operacao = operacao;
    }

    public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public CarteiraAtivo() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
}
