package prueba.tecnica.oxigent;

import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class H2DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDataSourceConnection() {
        assertNotNull(dataSource);
        try {
            dataSource.getConnection().close();
        } catch (Exception e) {
            fail("Fallo al realizar la conexión a la base de datos H2: " + e.getMessage());
        }
    }

    @Test
    public void testJdbcTemplateQuery() {
        assertNotNull(jdbcTemplate);
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM prices", Integer.class);
        assertTrue(count >= 0, "El recuento debe ser mayor a 0");
    }
}
