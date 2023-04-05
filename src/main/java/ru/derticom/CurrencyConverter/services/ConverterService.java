package ru.derticom.CurrencyConverter.services;

import org.springframework.stereotype.Service;
import ru.derticom.CurrencyConverter.model.ConversionResult;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ConverterService {
    private ApiService apiService;

    public ConverterService(ApiService apiService) {
        this.apiService = apiService;
    }

    public ConversionResult convert(String initCurrent, double initAmount, String destCurrent) {
        ApiResponce apiResponce = apiService.getCurrencyRateAndTime(initCurrent, destCurrent);

        ConversionResult conversionResult = new ConversionResult(initAmount, initCurrent, destCurrent,
        Math.round(initAmount * apiResponce.getRate() * 100.0) / 100.0, apiResponce.getRate(), apiResponce.getTimestamp());
        return conversionResult;

    }

    public String getDateAndTime(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = zonedDateTime.format(formatter);
        return formattedDate;
    }
}
