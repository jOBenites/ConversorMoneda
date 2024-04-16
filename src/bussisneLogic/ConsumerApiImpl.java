package bussisneLogic;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Conversor;
import model.ExchangerateResponse;
import utils.ApiConfiguracion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;

public class ConsumerApiImpl implements ConsumerApi {
    private final static String baseUrl = "https://v6.exchangerate-api.com/v6/";
    @Override
    public Conversor obtenerConversion(String monedaOrigen, String monedaDestino, double monto) {

        String url = baseUrl + ApiConfiguracion.API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + monto;
        //System.out.println("URL: " + url);
        try
        {
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .connectTimeout(Duration.ofSeconds(20))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMinutes(2))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            ExchangerateResponse result = gson.fromJson(response.body(), ExchangerateResponse.class);
            return new Conversor(result, monto);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> obtenerMonedas(int opcionSeleccionada) {
        HashMap<String, String> monedas = new HashMap<>();

        switch (opcionSeleccionada) {
            case 1 -> {
                monedas.put("MONEDA_ORIGEN", "USD");
                monedas.put("MONEDA_DESTINO", "ARS");
            }
            case 2 -> {
                monedas.put("MONEDA_ORIGEN", "ARS");
                monedas.put("MONEDA_DESTINO", "USD");
            }
            case 3 -> {
                monedas.put("MONEDA_ORIGEN", "USD");
                monedas.put("MONEDA_DESTINO", "BRL");
            }
            case 4 -> {
                monedas.put("MONEDA_ORIGEN", "BRL");
                monedas.put("MONEDA_DESTINO", "USD");
            }
            case 5 -> {
                monedas.put("MONEDA_ORIGEN", "USD");
                monedas.put("MONEDA_DESTINO", "PEN");
            }
            case 6 -> {
                monedas.put("MONEDA_ORIGEN", "PEN");
                monedas.put("MONEDA_DESTINO", "USD");
            }
            case 7 -> {
                monedas.put("MONEDA_ORIGEN", "EXIT");
                monedas.put("MONEDA_DESTINO", "EXIT");
            }
            default -> {
                monedas.put("MONEDA_ORIGEN", "USD");
                monedas.put("MONEDA_DESTINO", "COP");
            }
        }

        return monedas;
    }

}
