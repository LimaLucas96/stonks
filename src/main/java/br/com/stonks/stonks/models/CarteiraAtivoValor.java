package br.com.stonks.stonks.models;

public class CarteiraAtivoValor {

    private CarteiraAtivo carteiraAtivo;
    private Float valorMomento;
    public Float lucro;

    public CarteiraAtivoValor() {
    }

    public CarteiraAtivoValor(CarteiraAtivo carteiraAtivo, Float valorMomento, Float lucro) {
        this.carteiraAtivo = carteiraAtivo;
        this.valorMomento = valorMomento;
        this.lucro = lucro;
    }

    public CarteiraAtivo getCarteiraAtivo() {
        return carteiraAtivo;
    }

    public void setCarteiraAtivo(CarteiraAtivo carteiraAtivo) {
        this.carteiraAtivo = carteiraAtivo;
    }

    public Float getValorMomento() {
        return valorMomento;
    }

    public void setValorMomento(Float valorMomento) {
        this.valorMomento = valorMomento;
    }

    public Float getLucro() {
        return lucro;
    }

    public void setLucro(Float lucro) {
        this.lucro = lucro;
    }
}
