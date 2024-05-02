package prueba.tecnica.oxigent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.tecnica.oxigent.dto.PricesDto;
import prueba.tecnica.oxigent.service.PricesService;

import javax.validation.constraints.NotNull;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/price")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping("")
    public ResponseEntity<PricesDto> consultarPrecio(
            @RequestParam("fechaAplicacion") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @NotNull Date fechaAplicacion,
            @RequestParam("idProducto") @NotNull Long idProducto,
            @RequestParam("idCadena") @NotNull Long idCadena) {
        PricesDto response = pricesService.productPriceByDateAplication(fechaAplicacion, idProducto, idCadena);
        return ResponseEntity.ok(response);
    }

}
