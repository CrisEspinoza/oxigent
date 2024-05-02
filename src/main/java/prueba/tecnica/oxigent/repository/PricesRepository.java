package prueba.tecnica.oxigent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.tecnica.oxigent.model.Prices;

import java.util.Date;

@Repository("pricesRepository")
public interface PricesRepository extends JpaRepository<Prices, String> {
    Prices findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long brandId, Long productId, Date startDate, Date endDate);
}