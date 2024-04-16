package model;

public record ExchangerateResponse(String result, String baseCode, String targetCode, Double conversionRate, Double conversionResult) {
}
