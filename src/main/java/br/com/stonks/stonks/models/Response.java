package br.com.stonks.stonks.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty
    private Chart chart;

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    @Override
    public String toString() {
        Meta meta = getChart().getResult()[0].getMeta();
        return "chart{" +
                "currency='" + meta.getCurrency() + "'/ preco='"+meta.getRegularMarketPrice()+"'/ symbol='"+meta.getSymbol()+"'}";
    }

    public String getTabelaDados(){
        Quote quote = getChart().getResult()[0].getIndicators().getQuote()[0];
        Float[] valores = quote.getOpen();
        String retorno = "[[\"tempo\", \"Valor\"]";

        for (int i = 0 ; i < valores.length; i ++) {
            if (valores[i] != null) {
                retorno += ",";
                retorno += "[" + i + "," + valores[i] + "]";
            }
        }

        retorno += "]";
        return retorno;
    }

    public Response() {

    }
}
