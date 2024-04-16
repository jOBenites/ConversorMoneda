package model;

public class Conversor {
    private String monedaOrigen;
    private String monedaDestino;
    private double montoValor;
    private double montoConversion;

    private double montoIngresado;

    public Conversor(String monedaOrigen, String monedaDestino, double montoValor, double montoConversion) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.montoValor = montoValor;
        this.montoConversion = montoConversion;
    }

    public Conversor(ExchangerateResponse response, double montoIngresado){
        this.monedaOrigen = response.baseCode();
        this.monedaDestino = response.targetCode();
        this.montoValor = response.conversionRate();
        this.montoConversion = response.conversionResult();
        this.montoIngresado = montoIngresado;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public double getMontoValor() {
        return montoValor;
    }

    public void setMontoValor(double montoValor) {
        this.montoValor = montoValor;
    }

    public double getMontoConversion() {
        return montoConversion;
    }

    public void setMontoConversion(double montoConversion) {
        this.montoConversion = montoConversion;
    }

    @Override
    public String toString() {
        return "\nEl valor " + montoIngresado + " [" + monedaOrigen + "] corresponde al valor final de => " +
                montoConversion + " [" + monedaDestino + "] \n";
    }
}
