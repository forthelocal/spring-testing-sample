package jp.co.forthelocal.template.spring.domain.repositories;

import jp.co.forthelocal.template.spring.domain.entities.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user WHERE name = #{name} limit 1")
	User findByName(@Param("name") String name);

	@Select("SELECT * FROM user")
	List<User> findAll();

	@Insert("insert into user(name, email) values(#{name}, #{email})")
	@Options(useGeneratedKeys = true)
	int insert(User user);
}
