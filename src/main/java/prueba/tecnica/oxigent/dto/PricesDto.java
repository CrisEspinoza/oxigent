package prueba.tecnica.oxigent.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class PricesDto {

    private Long productId;
    private Long brandId;
    private BigDecimal price;
    private Integer priceList;
    private String dateAplication;
}
