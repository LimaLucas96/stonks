package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivoValorAbstract;

public class CarteiraAtivoValor extends DespesaAtivoValorAbstract {

    public Float lucro;

    public CarteiraAtivoValor() {
    }

    public CarteiraAtivoValor(CarteiraAtivo carteiraAtivo, Float valorMomento, Float lucro) {
        super(carteiraAtivo, valorMomento);
        this.lucro = lucro;
    }

    public Float getLucro() {
        return lucro;
    }

    public void setLucro(Float lucro) {
        this.lucro = lucro;
    }
}
