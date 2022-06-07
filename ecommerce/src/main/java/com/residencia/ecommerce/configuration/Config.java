package com.residencia.ecommerce.configuration;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class Config {
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("basicScheme",
							new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("basic"))
						.addParameters("myHeader1",
							new Parameter()
								.in("header")
								.schema(new StringSchema())
									.name("myHeader1"))
						.addHeaders("myHeader2",
							new Header()
								.description("myHeader2 header")
								.schema(new StringSchema())))
						.info(new Info()
							.title("e-Commerce API Restful Serratec")
							.version(appVersion)
							.description("API Exemplar de um e-Commerce em API Restful através do Serratec com uso de Spring Boot e outras dependências. Mais informações sobre a API: [https://github.com/pachecosamuel/API-FINAL-PROJECT](https://github.com/pachecosamuel/API-FINAL-PROJECT) | Mais informaçoes sobre o Swagger: [http://swagger.io](http://swagger.io).")
							.termsOfService("http://swagger.io/terms/")
						.license(new License()
							.name("Apache 2.0")
							.url("http://springdoc.org")));
	}

	@Bean
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.addTemplateResolver((templateResolver()));

		return springTemplateEngine;
	}
}