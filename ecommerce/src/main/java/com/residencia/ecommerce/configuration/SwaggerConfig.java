package com.residencia.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("basicScheme",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
						.addParameters("myHeader1",
								new Parameter().in("header").schema(new StringSchema()).name("myHeader1"))
						.addHeaders("myHeader2",
								new Header().description("myHeader2 header").schema(new StringSchema())))
				.info(new Info().title("Comercio API Restful Serratec").version(appVersion).description(
						"API Exemplar de um e-Commerce em API Restful através do Serratec. Mais informaçoes sobre o Swagger: [http://swagger.io](http://swagger.io).")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

	@Bean
	public ITemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		return templateResolver;
	}

	@Bean
	public TemplateEngine templateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());

		return templateEngine;
	}
}