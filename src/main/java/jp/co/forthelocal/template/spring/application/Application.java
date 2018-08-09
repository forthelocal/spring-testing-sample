package jp.co.forthelocal.template.spring.application;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication
@MapperScan("jp.co.forthelocal.template.spring.domain.repositories")
public class Application {

	public static void main(String[] args) {
		setDefaultProfile(args);
		SpringApplication.run(Application.class, args);
	}

	private static void setDefaultProfile(String[] args) {
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		if (!source.containsProperty("spring.profiles.active") &&
				!System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		}
	}

}
