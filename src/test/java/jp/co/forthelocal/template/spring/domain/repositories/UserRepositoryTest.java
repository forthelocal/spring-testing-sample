package jp.co.forthelocal.template.spring.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import jp.co.forthelocal.template.spring.domain.entities.User;
import jp.co.forthelocal.template.spring.test.PojoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("UserRepository単体テスト")
class UserRepositoryTest extends PojoTest {

	private User expected = objectMother.bear("user", User.class);

	@Test
	@DisplayName("サンプルテスト")
	void sample() {
		assertThat(true).isTrue();
	}

	@DisplayName("データが1件のとき")
	@Nested
	class OneRecord {

		@BeforeEach
		void setup() {
			userRepository.insert(expected);
		}

		@Test
		@DisplayName("名前でデータが取得できること")
		void findByName() {
			User actual = userRepository.findByName(expected.getName());
			assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
		}
	}

}
