package jp.co.forthelocal.template.spring.test;

import java.io.Reader;
import java.sql.SQLException;

import io.beanmother.core.ObjectMother;
import jp.co.forthelocal.template.spring.domain.repositories.UserRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(Bootstrap.class)
public class PojoTest {
	private static SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;

	protected UserRepository userRepository;
	protected static ObjectMother objectMother = ObjectMother.getInstance();

	@BeforeAll
	static void setUp() throws Exception {
		// create an SqlSessionFactory
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
	}

	@BeforeEach
	void setup() throws SQLException {
		sqlSession = sqlSessionFactory.openSession();
		sqlSession.getConnection().setAutoCommit(false);
		userRepository = sqlSession.getMapper(UserRepository.class);
	}

	@AfterEach
	void teardown() {
		sqlSession.rollback();
		sqlSession.close();
	}

}
