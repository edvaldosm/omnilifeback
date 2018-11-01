package com.br.omnilife.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
	@Bean
	public Docket detalheApi() {
 
		Docket docket = new Docket(DocumentationType.SPRING_WEB);
 		docket.select()
		.apis(RequestHandlerSelectors.basePackage("com.br.omnilife.restcontroller"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build())
		.useDefaultResponseMessages(false);
 
		return docket;
	}
 
	private ApiInfoBuilder informacoesApi() {
 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Api-Gerenciamento de Clientes da OmniLife");
		apiInfoBuilder.description("Api para realização do controle de clientes, produtos e compras realizadas");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.license("Licença - Open Source");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	private Contact contato() {
 
		return new Contact(
				"Edvaldo Matos/Lilian Demeter",
				null, 
				null);
	}
}