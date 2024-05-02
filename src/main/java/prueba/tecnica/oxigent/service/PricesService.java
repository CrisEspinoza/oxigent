package prueba.tecnica.oxigent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.tecnica.oxigent.dto.PricesDto;
import prueba.tecnica.oxigent.excepciones.NotFoundException;
import prueba.tecnica.oxigent.excepciones.PricesServiceException;
import prueba.tecnica.oxigent.mapper.PricesMapper;
import prueba.tecnica.oxigent.model.Prices;
import prueba.tecnica.oxigent.repository.PricesRepository;

import java.util.Date;

@Service
public class PricesService{

    @Autowired
    private PricesRepository pricesRepository;

    public PricesDto productPriceByDateAplication(Date fechaAplicacion, Long idProducto, Long idCadena) {
        try {
            Prices prices = pricesRepository.findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(idCadena,
                    idProducto, fechaAplicacion, fechaAplicacion);
            if (prices == null) {
                throw new NotFoundException("No se encontraron precios para los parámetros especificados");
            }
            return PricesMapper.toPricesToPricesDto(prices);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new PricesServiceException("Error al buscar precios", e);
        }
    }
}
