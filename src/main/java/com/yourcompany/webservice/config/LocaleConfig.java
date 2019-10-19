package com.yourcompany.webservice.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class LocaleConfig {

	@Bean
	public MessageSource messageSource() {
	    Locale.setDefault(Locale.ENGLISH);
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.addBasenames("classpath:org/springframework/security/messages");
	    return messageSource;
	}
}
