package blog.mapper;

import blog.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Select("select * from t_type")
    public List<Type> listTypes();

    @Insert("insert into t_type(name, introduce, avatar)" +
            " values (#{type.name}, #{type.introduce}, #{type.avatar})")
    public int saveType(@Param("type") Type type);

    @Select("select * from t_type where t_type.name = #{name}")
    public Type getTypeByName(String name);

    @Select("select * from t_type where t_type.id = #{id}")
    public Type getTypeById(Long id);

    @Delete("delete from t_type where t_type.id = #{id}")
    public int delete(Long id);

    @Update("update t_type set t_type.name = #{type.name}, t_type.introduce = #{type.introduce}, t_type.avatar = #{type.avatar} " +
            "where t_type.id = #{type.id}")
    public int updateType(@Param("type") Type type);


    /**
     * 根据id获取某一分类的名称
     * @param id
     * @return
     */
    @Select("select name from t_type where t_type.id = #{id}")
    public Type getNameById(Long id);

}
