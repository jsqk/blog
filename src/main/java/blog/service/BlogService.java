package blog.service;

import blog.entity.Blog;
import blog.vo.Message;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BlogService {
    /**
     * 往数据库库中添加一条博客数据
     * @param blog
     * @return
     */
    public Message saveBlog(Blog blog, Long id);

    /**
     * 获取已经发布博客的数量
     * @return
     */
    public int countOfPublic();

    /**
     * 返回某个用户的一页博客信息
     * @param userId
     * @return
     */
    public PageInfo<Blog> getOnePageByUser(Long userId, int page, int pageSize);

    /**
     * 根据id值返回一个博客的具体信息
     * @param id
     * @return
     */
    public Blog getById(Long id);

    /**
     * 获取一条博客内容转换后的博客信息，可以用于博客详情
     * @param id
     * @return
     */
    public Blog getBlogAndConvert(Long id);

    /**
     * 返回所有博客信息中的一页，无论是否发布
     * @param page:
     * @param pageSize:
     * @return
     */
    public PageInfo<Blog> getOnePage(int page, int pageSize);

    /**
     * 返回所有已经发布博客信息中的一页
     * @param page:
     * @param pageSize:
     * @return
     */
    public PageInfo<Blog> getOnePagePublic(int page, int pageSize);

    /**
     * 返回一页已经发布的某分类的博客信息
     * @param typeId
     * @return
     */
    public List<Blog> getOnePagePublicByType(Long typeId);

    /**
     * 根据id值删除一条博客
     * @param id
     * @return
     */
    public Message deleteById(Long id);

    /**
     * 更新指定id的博客的信息
     * @return
     */
    public Message updateBlog(Blog blog);

    /**
     * 根据typeId获取相关的全部博客
     * @param typeId
     * @return
     */
    public PageInfo<Blog> getPublicByTypeId(Long typeId);
}
