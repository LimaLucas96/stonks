package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CarteiraUsuario {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "É preciso definir um usuario.")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToMany(mappedBy = "carteira_ativo", fetch = FetchType.LAZY)
    @Column(name = "ativos")
    private Set<Ativo> ativos = new HashSet<>();

    @Column(name = "status")
    private Boolean status;

    @Column( name = "data_atualizacao")
    private Date data_atualizacao;
    

    public CarteiraUsuario(
    		@NotNull(message = "Status é obrigatorio.") Usuario usuario,
    		@NotNull(message = "Status é obrigatorio.") Ativo ativo,
    		@NotNull(message = "Status é obrigatorio.") Boolean status, 
    		@NotNull(message = "Data da compra é obrigatório.") Date data_atualizacao) {
    	this.ativos.add(ativo);
    	this.status = status;
    	this.usuario = usuario;
        this.data_atualizacao = data_atualizacao;
    }

    public CarteiraUsuario() { }

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

	public Set<Ativo> getAtivos() {
		return ativos;
	}

	public void setAtivos(Set<Ativo> ativos) {
		this.ativos = ativos;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getdata_atualizacao() {
		return data_atualizacao;
	}

	public void setdata_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}


}
