package prueba.tecnica.oxigent.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import prueba.tecnica.oxigent.dto.PricesDto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
public class PricesServiceIntegrationTest {

    @Autowired
    private PricesService pricesService;

    @ParameterizedTest
    @MethodSource("successfulRequestsProvider")
    public void testConsultarPrecioSuccessfulRequest(Date fechaAplicacion, Long idProducto, Long idCadena, BigDecimal expected) {
        PricesDto result = pricesService.productPriceByDateAplication(fechaAplicacion, idProducto, idCadena);
        assertEquals(result.getPrice(), expected);
    }

    public static Stream<Object[]> successfulRequestsProvider() throws ParseException {
        return Stream.of(
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 10:00:00"), 35455L, 1L , new BigDecimal("35.50")},
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 16:00:00"), 35455L, 1L , new BigDecimal("25.45")},
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 21:00:00"), 35455L, 1L , new BigDecimal("35.50")},
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 10:00:00"), 35455L, 1L , new BigDecimal("30.50")},
                new Object[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-16 21:00:00"), 35455L, 1L , new BigDecimal("38.95")}
        );
    }
}
