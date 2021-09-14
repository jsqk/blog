package blog.mapper;

import blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where t_user.username = #{username} and t_user.password = #{password}")
    public User getUserByNameAndPwd(String username, String password);

    @Select("select * from t_user where t_user.username = #{username}")
    public User getUserByName(String username);

    @Select("select id, nickname, avatar, signature from t_user where t_user.id = #{id}")
    public User getUserById(Long id);
}
