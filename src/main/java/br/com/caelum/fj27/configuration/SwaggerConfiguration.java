package br.com.caelum.fj27.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.fj27.model.User;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.caelum.fj27")).paths(PathSelectors.ant("/api/**"))
				.build()
				.ignoredParameterTypes(User.class)
				.globalOperationParameters(
						Arrays.asList(
								new ParameterBuilder().name("Authorization")
								.description("HEADER PARA FACILITAR O ENVIO DE BEARER")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(false)
								.build()))
				
				.apiInfo(apiInfo()).globalResponseMessage(RequestMethod.GET,
						Arrays.asList(
								new ResponseMessageBuilder().code(500).message("Xi... Algum programador...").build(),
								new ResponseMessageBuilder().code(403)
										.message("Forbidden! Você não pode acessar este recurso!").build(),
								new ResponseMessageBuilder().code(404).message("Recurso não encontrado").build()));

	}

	private ApiInfo apiInfo() {
		Contact contato = new Contact("Caelum", "http://cursos.alura.com.br/", "contato@alura.com.br");

		return new ApiInfoBuilder().title("Alura Forum API Documentation")
				.description(
						"Esta é a documentação interativa da Rest Api do fórum Alura. Tente navegar algum request!")
				.version("1.0").contact(contato).build();
	}

}
