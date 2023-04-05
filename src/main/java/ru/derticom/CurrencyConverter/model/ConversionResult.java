package ru.derticom.CurrencyConverter.model;

public class ConversionResult {
    private double initAmount;
    private String initCurrency;
    private String destCurrency;
    private double convertedAmount;
    private double rate;
    private long timestamp;

    public ConversionResult(double initAmount, String initCurrency, String destCurrency, double convertedAmount, double rate, long timestamp) {
        this.initAmount = initAmount;
        this.initCurrency = initCurrency;
        this.destCurrency = destCurrency;
        this.convertedAmount = convertedAmount;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public double getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(double initAmount) {
        this.initAmount = initAmount;
    }

    public String getInitCurrency() {
        return initCurrency;
    }

    public void setInitCurrency(String initCurrency) {
        this.initCurrency = initCurrency;
    }

    public String getDestCurrency() {
        return destCurrency;
    }

    public void setDestCurrency(String destCurrency) {
        this.destCurrency = destCurrency;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
