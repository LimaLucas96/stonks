package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Carteira {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "É preciso definir um usuario.")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToMany(mappedBy = "carteira_ativo", fetch = FetchType.LAZY)
    private int carteira_ativo_id;

    @Column(name = "status")
    private Boolean status;

    @Column( name = "data_atualizacao")
    private Date data_atualizacao;
    

    public Carteira(
    		@NotNull(message = "Status é obrigatorio.") Usuario usuario) {
    	this.status = true;
    	this.usuario = usuario;
        this.data_atualizacao = new Date();
    }

    public Carteira() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getCarteira_ativo_id() {
		return carteira_ativo_id;
	}

	public void setCarteira_ativo_id(int carteira_ativo_id) {
		this.carteira_ativo_id = carteira_ativo_id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getData_atualizacao() {
		return data_atualizacao;
	}

	public void setData_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}


}
