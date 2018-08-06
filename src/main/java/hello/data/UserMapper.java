package hello.data;

import hello.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
  @Select("SELECT * FROM user WHERE name = #{name}")
  User findByName(@Param("name") String state);

  @Select("SELECT * FROM user")
  List<User> findAll();

  @Insert("insert into user(name, email) values(#{name}, #{email})")
  @Options(useGeneratedKeys=true)
  int insert(User user);
}
