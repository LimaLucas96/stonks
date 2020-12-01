package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Entity
public class CarteiraAtivo extends DespesaAtivo {

	@Enumerated(EnumType.STRING)
	private Operacao operacao;

	public CarteiraAtivo() {
	}

    public CarteiraAtivo(
    		@NotNull(message = "Carteira é obrigatoria.") Carteira carteira,
    		@NotNull(message = "Ativo é obrigatorio.") Ativo ativo,
    		@NotNull(message = "Valor é obrigatorio.") double valor,
    		@NotNull(message = "Quantidade é obrigatoria.") int quantidade,
    		@NotNull(message = "Data da compra é obrigatória.") Date data_compra,
			@NotNull(message = "Data da compra é obrigatória.") Operacao operacao) {

    	super(carteira, ativo, valor, quantidade, data_compra);
    	this.operacao = operacao;
    }

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
}
