package com.yourcompany.webservice.config;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.http.ResponseEntity;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
@Profile("!tests")
@SuppressWarnings("deprecation")
public class SwaggerConfig {

	public static final String BEARER = "apiKey";

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
		return new Docket(DocumentationType.OAS_30)
				.alternateTypeRules(newRule(typeResolver.resolve(Pageable.class), pageableMixin(restConfiguration),
						Ordered.HIGHEST_PRECEDENCE))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yourcompany.webservice.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .additionalModels(new TypeResolver().resolve(ErrorMessage.class))
                .useDefaultResponseMessages(false)
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(securityScheme()))
                .enableUrlTemplating(false);
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
//
//	private ApiKey apiKey() {
//		return new ApiKey(BEARER, "Authorization", "header");
//	}
	
	private ApiKey securityScheme() {
	    return new ApiKey("apiKey", "apiKey", "header");
	 }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(operationContext ->
                        operationContext.requestMappingPattern().startsWith("/api")
                )
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
    }
}