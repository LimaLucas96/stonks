package br.com.stonks.stonks.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Indicators {
    private Quote[] quote;

    public Quote[] getQuote() {
        return quote;
    }

    public void setQuote(Quote[] quote) {
        this.quote = quote;
    }
}
