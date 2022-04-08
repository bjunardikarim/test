package id.apps.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "id.apps.*"})
//@EnableJpaRepositories(basePackages = { "koropm.base", "koropm.id" })
//@EntityScan(basePackages= {"koropm.base.model","koropm.id.opm000.model"})
public class AppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
