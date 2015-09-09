package br.com.brunolandia.sisvarejo;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author Henrique Lobato Zago
 *
 */
@SpringBootApplication
@PropertySource({ "classpath:/config/datasource.properties",
		"classpath:/config/jpa.properties",
		"classpath:/config/mail.properties",
		"classpath:/config/logging.properties", })
@ImportResource("classpath:/META-INF/spring/sisvarejo-context.xml")
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 
	 */
	public static final String AUDIT_SCHEMA = "auditoria";
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param args
	 */
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
	{
		return application.sources( Application.class );
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dwrSpringServletRegistration() {
		final ServletRegistrationBean registration = new ServletRegistrationBean(
				new DwrSpringServlet(), "/broker/*");
		registration.addInitParameter("debug", "true");
		registration.addInitParameter( "accessLogLevel", "ERROR" );
		registration.setName("dwrSpringServlet");
		return registration;
	}

	/**
	 *
	 */
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	protected static class ApplicationSecurity extends
			WebSecurityConfigurerAdapter {
		/**
    	 * 
    	 */
		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.csrf().disable();
			httpSecurity.headers().frameOptions().disable();
		}
	}
}
