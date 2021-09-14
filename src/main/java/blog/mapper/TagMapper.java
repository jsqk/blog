package blog.mapper;

import blog.entity.Tag;
import blog.vo.TagCard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {
    /**
     * 根据一个id获取tag信息
     * @param id
     * @return
     */
    @Select("select * from t_tag where t_tag.id = #{id}")
    public Tag getTageById(Long id);

    /**
     * 获取所有的tag
     * @return
     */
    @Select("select * from t_tag")
    public List<Tag> listAllTag();

    /**
     * 往数据库中插入一条标签信息
     * @param name 标签的名称
     * @return
     */
    @Insert("insert into t_tag(name) values (#{name})")
    public int saveTag(String name);

    /**
     * 根据名字获取一条tag信息
     * @param name
     * @return
     */
    @Select("select * from t_tag where t_tag.name = #{name}")
    public Tag getTageByName(String name);

    /**
     * 根据id删除一条tag
     * @param id
     * @return
     */
    @Delete("delete from t_tag where t_tag.id = #{id}")
    public int deleteById(Long id);


}
