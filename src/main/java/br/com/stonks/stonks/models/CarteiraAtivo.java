package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CarteiraAtivo {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;
    
    @ManyToMany(mappedBy = "ativo_id", fetch = FetchType.LAZY)
    private Ativo ativo;

    @Column(name = "valor")
    private double valor;
    
    @Column(name = "quantidade")
    private int quantidade;

    @Column( name = "data_compra")
    private Date data_compra;
    

    public CarteiraAtivo(
    		@NotNull(message = "Status é obrigatorio.") Carteira carteira,
    		@NotNull(message = "Status é obrigatorio.") Ativo ativo,
    		@NotNull(message = "Status é obrigatorio.") double valor,
    		@NotNull(message = "Status é obrigatorio.") int quantidade,
    		@NotNull(message = "Data da compra é obrigatório.") Date data_compra) {
    	this.ativo = ativo;
    	this.carteira = carteira;
    	this.valor = valor;
    	this.quantidade = quantidade;
        this.data_compra = data_compra;
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

	public Date getData_compra() {
		return data_compra;
	}

	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}

	public CarteiraAtivo() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
