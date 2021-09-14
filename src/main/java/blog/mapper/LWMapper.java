package blog.mapper;

import blog.entity.LeaveWord;
import blog.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface LWMapper {
    /**
     * 获取所有留言
     */
    @Select("select * from t_lw where parent_id = 0")
    @Results(id = "leaveWord", value = {
            @Result(column = "id",property = "childLeaveWord",
                    many=@Many(select="blog.mapper.LWMapper.getChild",fetchType= FetchType.LAZY)),
            @Result(column = "parent_id",property = "parentId"),
            @Result(column = "id",property = "id"),
            @Result(column = "parent_id",property = "parentLW",
                    many=@Many(select="blog.mapper.LWMapper.getByIdOnlySelf",fetchType= FetchType.LAZY)),
    })
    public List<LeaveWord> getAll();

    @Select("select * from t_lw where parent_id = #{parent_id}")
    @ResultMap(value = "leaveWord")
    public List<LeaveWord> getChild(int parent_id);

    @Insert("insert into t_lw(username, content, create_time, email, parent_id, status) values" +
            " (#{lw.username}, #{lw.content}, #{lw.createTime}, #{lw.email}, #{lw.parentId}, #{lw.status})")
    public void insert(@Param("lw") LeaveWord leaveWord);

    @Select("select * from t_lw where id = #{id}")
    @ResultMap(value = "leaveWord")
    public LeaveWord getById(int id);

    @Select("select * from t_lw where id = #{id}")
    public LeaveWord getByIdOnlySelf(int id);
}
