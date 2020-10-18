package br.com.stonks.stonks.models;

public enum Operacao {
    VENDA {
        @Override
        public String getDenominacao() {
            return "Venda";
        }
    },
    COMPRA {
        @Override
        public String getDenominacao() { return "Compra"; }

    };

    public abstract String getDenominacao();
}
