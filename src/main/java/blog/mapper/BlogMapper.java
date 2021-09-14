package blog.mapper;

import blog.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface BlogMapper {

    /**
     * 插入一条博客信息，必须要有用户id
     * @param blog
     * @param userId
     */
    @Insert("insert into t_blog(title, content, introduce, user_id, type_id, status, views, create_time, update_time, tagIds) values " +
            "(#{blog.title}, #{blog.content}, #{blog.introduce}, #{userId}, #{blog.type.id}, #{blog.status}," +
            " #{blog.views},#{blog.createTime}, #{blog.updateTime}, #{blog.tagIds})")
    @Options(useGeneratedKeys=true, keyProperty="blog.id", keyColumn="id")
    public void insertBlog(@Param("blog") Blog blog, Long userId);


    /**
     * 获取某一用户的所有博客
     * @param userId
     * @return
     */
    @Select("select * from t_blog, t_type, t_user where t_blog.user_id = t_user.id and t_blog.type_id = t_type.id" +
            " and t_blog.user_id = #{userId}")
    @ResultMap(value = "blog")
    public List<Blog> getByUserId(Long userId);

    /**
     * 获取某一type_id对应的已经发布的博客数量
     * @param typeId
     * @return
     */
    @Select("select count(*) from t_blog, t_type, t_user where t_blog.user_id = t_user.id and t_blog.type_id = t_type.id" +
            " and t_blog.type_id = #{typeId} and t_blog.status = 1")
    public int countsOfOneType(Long typeId);


    @Select("select count(*) from t_blog, t_type, t_user where t_blog.user_id = t_user.id and t_blog.type_id = t_type.id" +
            " and t_blog.status = 1")
    public int countOfPublic();

    /**
     * 获取已经发布的某个分类的全部博客信息
     * @param typeId
     * @return
     */
    @Select("select * from t_blog, t_type, t_user where t_blog.user_id = t_user.id and t_blog.type_id = t_type.id " +
            " and t_blog.type_id = #{typeId} and t_blog.status = 1")
    @Results(id = "blog", value = {
            @Result(column = "type_id",property = "type",
                    javaType = Type.class,
                    one=@One(select="blog.mapper.TypeMapper.getTypeById",fetchType= FetchType.LAZY)),
            @Result(property = "user",
                    column = "user_id",
                    javaType = User.class,
                    one=@One(select = "blog.mapper.UserMapper.getUserById",fetchType = FetchType.LAZY))
    })
    public List<Blog> getAllPublicByTypeId(Long typeId);

    /**
     * 根据博客id获取一条博客信息。
     * @param id
     * @return
     */
    @Select("select * from t_blog, t_type, t_user where t_blog.user_id = t_user.id and t_blog.type_id = t_type.id" +
            " and t_blog.id = #{id}")
    @ResultMap(value = "blog")
    public Blog getById(Long id);

    /**
     * 获取所有已经发布的博客信息
     * @return
     */
    @Select("select * from t_blog, t_type, t_user where t_blog.user_id = t_user.id and t_blog.type_id = t_type.id and t_blog.status = 1")
    @ResultMap(value = "blog")
    public List<Blog> getAllPublic();

    /**
     * 根据博客id删除一条博客
     * @param id
     */
    @Delete("delete from t_blog where t_blog.id = #{id}")
    public void deleteById(Long id);

    /**
     * 更新一条博客信息
     * @param blog
     */
    @Update("update t_blog set t_blog.update_time = #{blog.updateTime}," +
            " t_blog.title = #{blog.title}, t_blog.content = #{blog.content}," +
            " t_blog.tagIds = #{blog.tagIds}, t_blog.introduce = #{blog.introduce}," +
            " t_blog.status = #{blog.status} where t_blog.id = #{blog.id} ")
    public void update(@Param("blog") Blog blog);

}
