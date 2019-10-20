package com.yourcompany.webservice.config;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.lang.reflect.Type;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("!tests")
public class SwaggerConfig {

	public static final String BEARER = "Bearer";

	@Value("${oauth.client.id}")
	private String oauthClientId;

	@Value("${oauth.secret.key}")
	private String oauthSecretKey;

	@Value("${oauth.server.url}")
	private String oauthServerUrl;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	private final TypeResolver typeResolver;
	private final RepositoryRestConfiguration restConfiguration;

	public SwaggerConfig(TypeResolver typeResolver, RepositoryRestConfiguration restConfiguration) {
		this.typeResolver = typeResolver;
		this.restConfiguration = restConfiguration;
	}

	@Bean
	@ConditionalOnMissingBean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.alternateTypeRules(newRule(typeResolver.resolve(Pageable.class), pageableMixin(restConfiguration),
						Ordered.HIGHEST_PRECEDENCE))
				.select().apis(RequestHandlerSelectors.basePackage("com.mycompany.webservice.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(Arrays.asList(apiKey()));
	}

	/**
	 * API Infos
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("APPS API").description("").termsOfServiceUrl("https://www.example.com/api")
				.contact(new Contact("Danny Candra", "http://www.example.com", "dannycandra@hotmail.com"))
				.license("Danny Candra").licenseUrl("https://www.example.com").version("0.0.1").build();
	}

	/**
	 * Handling pageable in swagger ui
	 * 
	 * @param restConfiguration
	 * @return Type {@link Type}
	 */
	private Type pageableMixin(RepositoryRestConfiguration restConfiguration) {
		return new AlternateTypeBuilder()
				.fullyQualifiedClassName(String.format("%s.generated.%s", Pageable.class.getPackage().getName(),
						Pageable.class.getSimpleName()))
				.withProperties(Arrays.asList(property(Integer.class, restConfiguration.getPageParamName()),
						property(Integer.class, restConfiguration.getLimitParamName()),
						property(String.class, restConfiguration.getSortParamName())))
				.build();
	}

	/**
	 * Build property for swagger ui
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	private AlternateTypePropertyBuilder property(Class<?> type, String name) {
		return new AlternateTypePropertyBuilder().withName(name).withType(type).withCanRead(true).withCanWrite(true);
	}

	private ApiKey apiKey() {
		return new ApiKey(BEARER, "Authorization", "header");
	}
}