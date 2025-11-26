package PComponent_Eva3.PComponent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("PComponent API")
						.version("v0.0.1")
						.description("Documentación OpenAPI para PComponent")
						.contact(new Contact().name("Equipo PComponent").email("soporte@example.com"))
						.license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
	}

}
