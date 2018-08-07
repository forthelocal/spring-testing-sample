package jp.co.forthelocal.template.spring.application;

import jp.co.forthelocal.template.spring.domain.repositories.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.forthelocal.template.spring.domain.repositories")
public class Application implements CommandLineRunner {

	private final UserMapper userMapper;

	public Application(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.userMapper.findByName("First"));
	}
}