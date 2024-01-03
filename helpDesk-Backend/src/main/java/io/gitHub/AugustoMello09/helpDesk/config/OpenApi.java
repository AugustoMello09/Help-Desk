package io.gitHub.AugustoMello09.helpDesk.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = @Info(title = "Help Desk", version = "v1", description = "Documentação da aplicação Help desk."))
public class OpenApi {
	
	@Bean
	public OpenAPI api() {
		return new OpenAPI().components(new Components())
				.info(new io.swagger.v3.oas.models.info.Info().title("Help Desk").version("v1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
