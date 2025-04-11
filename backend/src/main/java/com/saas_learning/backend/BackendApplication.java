package com.saas_learning.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableWebSecurity
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Value("${allowed.origin}")
	private String[] allowedOrigins;

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		// Ajouter les origines autorisées depuis le fichier .properties
		for (String origin : allowedOrigins) {
			config.addAllowedOrigin(origin);
		}

		// Activer les en-têtes et les méthodes HTTP nécessaires
		config.addAllowedHeader("*"); // Autorise tous les en-têtes
		config.addAllowedMethod("*"); // Autorise toutes les méthodes HTTP
		config.setAllowCredentials(true); // Autorise les cookies/sessions
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
