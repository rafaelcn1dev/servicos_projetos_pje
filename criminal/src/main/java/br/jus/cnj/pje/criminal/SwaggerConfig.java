package br.jus.cnj.pje.criminal;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket v1Api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("criminal-v1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.jus.cnj.pje.criminal.controllers.v1"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	@Bean
	public Docket v2Api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("criminal-v2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.jus.cnj.pje.criminal.controllers.v2"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	

	private ApiInfo getApiInfo() {
		return new ApiInfo("PJe - Criminal", "API do PJe - Criminal", "2.0", "TERMS OF SERVICE URL",
				new Contact("Conselho Nacional de Justiça", "https://www.cnj.jus.br", ""), null, null, Collections.emptyList());
	}
}
