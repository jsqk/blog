package blog.mapper;

import blog.entity.TagAndBlog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface BATMapper {
    /**
     * 删除t_bat表中所有和blogId相关的信息
     * @param blogId
     */
    @Delete("delete from t_bat where t_bat.blog_id = #{blogId}")
    public void deleteBlogTagByBlogId(Long blogId);

    /**
     * 根据博客id和tagId删除某一条bat信息
     * @param tagId
     * @param blogId
     */
    @Delete("delete from t_bat where t_bat.blog_id = #{blogId} and t_bat.tag_id = #{tagId}")
    public void deleteBlogTagByBlogIdAndTagId(Long tagId, Long blogId);

    /**
     * 根据tagId、blogId的值，判断数据库中是否存在该数据（存在为1，不存在为0）
     * @param tagId
     * @param blogId
     * @return
     */
    @Select("select count(*) from t_bat where t_bat.tag_id = #{tagId} and t_bat.blog_id = #{blogId}")
    public int count(Long tagId, Long blogId);

    /**
     * 获取tagId在表中的数量，可以对应到有多少个含有该tag的博客
     * @param tagId
     * @return
     */
    @Select("select count(*) from t_bat where t_bat.tag_id = #{tagId}")
    public int countOfTagId(Long tagId);

    /**
     * 向bat表中插入一条数据
     * @param blogId
     * @param tagId
     */
    @Insert("insert into t_bat(blog_id, tag_id) values (#{blogId}, #{tagId})")
    public void insertBAT(Long blogId, Long tagId);

    /**
     * 根据博客id查找出所有的bat数据
     * @Param blogId
     * @return
     */
    @Select("select * from t_bat where t_bat.blog_id = #{id}")
    public List<TagAndBlog> getTagOfBlogById(Long blogId);

    /**
     * 根据博客id删除bat表中的所有数据
     * @param blogId
     */
    @Delete("delete from t_bat where t_bat.blog_id = #{blogId}")
    public void deleteByBlogId(Long blogId);
}
