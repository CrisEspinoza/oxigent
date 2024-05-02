package prueba.tecnica.oxigent.mapper;

import org.springframework.beans.BeanUtils;
import prueba.tecnica.oxigent.dto.PricesDto;
import prueba.tecnica.oxigent.model.Prices;

public class PricesMapper {

    public static PricesDto toPricesToPricesDto(Prices prices) {
        PricesDto priceToPriceDto = PricesDto.builder().build();
        BeanUtils.copyProperties(prices, priceToPriceDto);
        return priceToPriceDto;
    }
}
