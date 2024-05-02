package prueba.tecnica.oxigent.excepciones;

public class PricesServiceException extends RuntimeException {
    public PricesServiceException(String message) {
        super(message);
    }

    public PricesServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}