package bussisneLogic;

import model.Conversor;

import java.util.HashMap;

public interface ConsumerApi {

    Conversor obtenerConversion(String monedaOrigen, String monedaDestino, double monto);

    HashMap<String, String> obtenerMonedas(int opcionSeleccionda);
}
