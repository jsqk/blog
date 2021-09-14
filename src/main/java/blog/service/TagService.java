package blog.service;

import blog.entity.Tag;
import blog.vo.Message;
import blog.vo.TagCard;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface TagService {

    /**
     * 根据一个id获取tag信息
     * @param id
     * @return
     */
    public Tag getTageById(Long id);

    /**
     * 获取所有的tag
     * @return
     */
    public List<Tag> listAllTag();

    /**
     * 获取大小为pagesize个标签信息
     * @param page
     * @param pageSize
     * @return
     */
    public PageInfo<Tag> tagsOnePage(int page, int pageSize);

    /**
     * 往数据库中插入一条tag信息，返回message格式的信息。
     * @return
     */
    public Message insertTag(String name);

    /**
     * 根据名称获取一条tag信息
     * @param name
     * @return
     */
    public Tag getTagByName(String name);

    /**
     * 根据id删除一条标签
     * @param id
     * @return
     */
    public Message deleteById(Long id);

    /**
     * 返回所有的标签信息以及该标签相关的博客数量
     * @return
     */
    public List<TagCard> allTagCard();

    /**
     * 根据博客id返回相关的tag信息
     * @param blogId
     * @return
     */
    public List<Tag> listTagOfBlog(Long blogId);
}
