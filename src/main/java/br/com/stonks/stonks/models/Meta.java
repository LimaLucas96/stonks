package br.com.stonks.stonks.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private float chartPreviousClose;
    private String currency;
    private String exchangeName;
    private String exchangeTimezoneName;
    private float previousClose;
    private Float regularMarketPrice;
    private String symbol;

    public float getChartPreviousClose() {
        return chartPreviousClose;
    }

    public void setChartPreviousClose(float chartPreviousClose) {
        this.chartPreviousClose = chartPreviousClose;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeTimezoneName() {
        return exchangeTimezoneName;
    }

    public void setExchangeTimezoneName(String exchangeTimezoneName) {
        this.exchangeTimezoneName = exchangeTimezoneName;
    }

    public float getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(float previousClose) {
        this.previousClose = previousClose;
    }

    public float getRegularMarketPrice() {
        return regularMarketPrice;
    }

    public void setRegularMarketPrice(float regularMarketPrice) {
        this.regularMarketPrice = regularMarketPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
