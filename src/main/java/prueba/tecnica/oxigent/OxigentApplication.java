package prueba.tecnica.oxigent;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@EnableEncryptableProperties
@SpringBootApplication
public class OxigentApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxigentApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
