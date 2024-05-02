package prueba.tecnica.oxigent.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import prueba.tecnica.oxigent.dto.PricesDto;
import prueba.tecnica.oxigent.excepciones.NotFoundException;
import prueba.tecnica.oxigent.excepciones.PricesServiceException;
import prueba.tecnica.oxigent.service.PricesService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {

    @Mock
    private PricesService pricesService;

    @InjectMocks
    private PricesController pricesController;


    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(pricesController, "pricesService", pricesService);
    }

    @ParameterizedTest
    @MethodSource("successfulRequestsProvider")
    public void testConsultarPrecioSuccessfulRequest(Date fechaAplicacion, Long idProducto, Long idCadena) {
        PricesDto expectedResponse = PricesDto.builder().build();
        when(pricesService.productPriceByDateAplication(fechaAplicacion, idProducto, idCadena)).thenReturn(expectedResponse);

        ResponseEntity<PricesDto> responseEntity = pricesController.consultarPrecio(fechaAplicacion, idProducto, idCadena);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @ParameterizedTest
    @MethodSource("notFoundRequestsProvider")
    public void testConsultarPrecioNotFoundException(Date fechaAplicacion, Long idProducto, Long idCadena) {
        when(pricesService.productPriceByDateAplication(fechaAplicacion, idProducto, idCadena))
                .thenThrow(new NotFoundException("No se encontraron precios para los parámetros especificados"));

        assertThrows(NotFoundException.class, () -> pricesController.consultarPrecio(fechaAplicacion, idProducto, idCadena));
    }

    @ParameterizedTest
    @MethodSource("serviceExceptionRequestsProvider")
    public void testConsultarPrecioServiceException(Date fechaAplicacion, Long idProducto, Long idCadena) {
        when(pricesService.productPriceByDateAplication(fechaAplicacion, idProducto, idCadena))
                .thenThrow(new PricesServiceException("Error al buscar precios"));

        assertThrows(PricesServiceException.class, () -> pricesController.consultarPrecio(fechaAplicacion, idProducto, idCadena));
    }

    public static Stream<Object[]> successfulRequestsProvider() throws ParseException {
        return Stream.of(
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-14 10:00:00"), 35455L, 1L },
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-14 16:00:00"), 35455L, 1L },
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-14 21:00:00"), 35455L, 1L },
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-15 10:00:00"), 35455L, 1L },
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-16 21:00:00"), 35455L, 1L }
        );
    }

    public static Stream<Object[]> notFoundRequestsProvider() throws ParseException {
        return Stream.of(
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-14 10:00:00"), 35455L, 1L },
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-15 10:00:00"), 35455L, 1L }
        );
    }

    public static Stream<Object[]> serviceExceptionRequestsProvider() throws ParseException {
        return Stream.of(
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-14 10:00:00"), 35455L, 1L },
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-06-15 10:00:00"), 35455L, 1L }
        );
    }
}
