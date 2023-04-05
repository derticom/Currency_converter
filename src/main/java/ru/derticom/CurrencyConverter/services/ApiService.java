package ru.derticom.CurrencyConverter.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ApiService {

    public ApiResponce getCurrencyRateAndTime(String initCurrent, String destCurrent) {
        //String apiUrl = "https://openexchangerates.org/api/latest.json?app_id=254012d59aa8464884c30b0bb33c2ea4&base=" + initCurrent + "&symbols=" + destCurrent;
        String apiUrl = "https://api.apilayer.com/exchangerates_data/latest?base=" + initCurrent + "&symbols=" + destCurrent +"&apikey=YCAZ0dZbRyHv7XdampogXvX5PhIQZwqK";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());

                JSONObject rates = jsonResponse.getJSONObject("rates");

                return new ApiResponce(rates.getDouble(destCurrent), jsonResponse.getInt("timestamp"));

            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ERROR HERE");
        return null;
    }

}

class ApiResponce {
    private double rate;
    private long timestamp;

    public ApiResponce(double rate, long timestamp) {
        this.rate = rate;
        this.timestamp = timestamp;
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