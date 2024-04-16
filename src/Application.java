import bussisneLogic.ConsumerApiImpl;
import model.Conversor;
import utils.ApiConfiguracion;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flagContinue = true;

        ConsumerApiImpl consumerApi = new ConsumerApiImpl();

        while (flagContinue)
        {
            System.out.println(ApiConfiguracion.MESSAGE_WELCOME);
            int opcionSeleccionada;
            try{
                opcionSeleccionada = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Por favor, ingresar una opción válida");
                opcionSeleccionada = 7;
            }

            HashMap<String, String> map = consumerApi.obtenerMonedas(opcionSeleccionada);
            String monedaOrigen = map.get("MONEDA_ORIGEN");
            String monedaDestino = map.get("MONEDA_DESTINO");

            if(monedaOrigen.equals("EXIT")) {
                flagContinue = false;
            } else {
                System.out.println("Ingrese el valor que desea convertir");
                double montoCalculo;
                try{
                    montoCalculo = sc.nextDouble();
                }catch (InputMismatchException e){
                    System.out.println("Por favor, ingresar un monto válido");
                    montoCalculo = 0;
                }

                Conversor conversor = consumerApi.obtenerConversion(monedaOrigen, monedaDestino, montoCalculo);
                System.out.println(conversor);
            }
        }

    }
}