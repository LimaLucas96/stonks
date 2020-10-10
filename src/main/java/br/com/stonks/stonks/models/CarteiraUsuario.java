package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class CarteiraUsuario {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "É preciso definir um idUsuario.")
    @Column(name = "id_usuario", unique=true)
    private int idUsuario;

    @Column(name = "status")
    private Boolean status;

    @Column( name = "ultima_atualizacao")
    private Date ultimaAtualizacao;
    

    public CarteiraUsuario(@NotNull(message = "id do Usuário é obrigatorio.") int idUsuario, @NotNull(message = "Status é obrigatorio.") Boolean satus, 
    		@NotNull(message = "Data de atualizacao é obrigatório.") Date ultimaAtualizacao) {
        this.idUsuario = idUsuario;
        this.status = status;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public CarteiraUsuario() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
