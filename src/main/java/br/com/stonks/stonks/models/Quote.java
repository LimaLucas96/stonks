package br.com.stonks.stonks.models;

public class Quote {
    private Float[] close;
    private Float[] high;
    private Float[] low;
    private Float[] open;

    public Float[] getClose() {
        return close;
    }

    public void setClose(Float[] close) {
        this.close = close;
    }

    public Float[] getHigh() {
        return high;
    }

    public void setHigh(Float[] high) {
        this.high = high;
    }

    public Float[] getLow() {
        return low;
    }

    public void setLow(Float[] low) {
        this.low = low;
    }

    public Float[] getOpen() {
        return open;
    }

    public void setOpen(Float[] open) {
        this.open = open;
    }
}
