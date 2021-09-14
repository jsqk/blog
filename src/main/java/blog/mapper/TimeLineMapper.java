package blog.mapper;

import blog.entity.TimeLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TimeLineMapper {

    @Select("select * from t_tl order by id DESC")
    @Results(id = "time", value = {
            @Result(column = "id", property = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "create_time", column = "createTime")
    })
    public List<TimeLine> getAllTimeLine();

}
