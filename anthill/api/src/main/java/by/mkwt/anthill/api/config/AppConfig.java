package by.mkwt.anthill.api.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import by.mkwt.anthill.dao.util.HibernateEntityManagerFactory;

@Configuration
public class AppConfig {

	@Bean
	EntityManagerFactory getEntityManagerFactory() {
		return HibernateEntityManagerFactory.FACTORY;
	}
	
}
