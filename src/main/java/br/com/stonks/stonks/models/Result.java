package br.com.stonks.stonks.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private Indicators indicators;
    private Meta meta;
    private Long[] timestamp;

    public Indicators getIndicators() {
        return indicators;
    }

    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }

    public Long[] getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long[] timestamp) {
        this.timestamp = timestamp;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
