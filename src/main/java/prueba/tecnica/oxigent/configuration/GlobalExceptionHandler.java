package prueba.tecnica.oxigent.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.HttpStatus;
import prueba.tecnica.oxigent.excepciones.NotFoundException;
import prueba.tecnica.oxigent.excepciones.PricesServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String mensajeError = "Parámetro faltante: " + ex.getParameterName();
        return ResponseEntity.badRequest().body(mensajeError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String mensajeError = "Error de tipo de argumento: " + ex.getName() + " debe ser de tipo " + ex.getRequiredType().getSimpleName();
        return ResponseEntity.badRequest().body(mensajeError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PricesServiceException.class)
    public ResponseEntity<String> handlePricesService(PricesServiceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
