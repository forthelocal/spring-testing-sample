package jp.co.forthelocal.template.spring.application;

import jp.co.forthelocal.template.spring.domain.repositories.UserRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.forthelocal.template.spring.domain.repositories")
public class Application implements CommandLineRunner {

	private final UserRepository userRepository;

	public Application(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.userRepository.findByName("First"));
	}
}
