package jp.co.forthelocal.template.spring.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Reader;
import java.sql.SQLException;

import jp.co.forthelocal.template.spring.domain.entities.User;
import jp.co.forthelocal.template.spring.domain.repositories.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserMapperTest {

	private static SqlSessionFactory sqlSessionFactory;
	private UserMapper userMapper;
	private SqlSession sqlSession;


	@BeforeAll
	public static void setUp() throws Exception {
		// create an SqlSessionFactory
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
	}

	@BeforeEach
	void setup() throws SQLException {
		sqlSession = sqlSessionFactory.openSession();
		sqlSession.getConnection().setAutoCommit(false);
		userMapper = sqlSession.getMapper(UserMapper.class);
		User u = new User();
		u.setName("Taro");
		u.setEmail("taro@example.com");
		userMapper.insert(u);
	}

	@AfterEach
	void teardown() {
		sqlSession.rollback();
		sqlSession.close();
	}

	@Test
	void findByName() {
		User u = userMapper.findByName("Taro");
		assertThat(u.getEmail()).isEqualTo("taro@example.com");
	}

}
