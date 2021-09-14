package blog.service.impl;

import blog.entity.Blog;
import blog.handler.NotFoundException;
import blog.mapper.BATMapper;
import blog.mapper.BlogMapper;
import blog.service.BlogService;
import blog.service.TagService;
import blog.util.MarkDownUtils;
import blog.util.Translate;
import blog.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private BATMapper batMapper;

    @Override
    public Message saveBlog(Blog blog, Long userId) {
        Message message;
        if(blog.getType() == null){
            message = new Message(false,"未选择博客分类");
            return message;
        }
        if("".equals(blog.getContent()) || blog.getContent() == null){
            message = new Message(false, "博客内容为空");
            return message;
        }
        Date createTime = new Date();
        blog.setCreateTime(createTime);
        blog.setUpdateTime(createTime);
        List<Long> ids = Translate.getTagIds(blog.getTagIds());
        blogMapper.insertBlog(blog, userId);
        for(Long a: ids){
            batMapper.insertBAT(blog.getId(), a-48);
        }
            message = new Message(true, "博客添加成功，博客标题为：【" + blog.getTitle() + "】");
        return message;
    }

    @Override
    public int countOfPublic() {
        return blogMapper.countOfPublic();
    }

    @Override
    public PageInfo<Blog> getOnePageByUser(Long userId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Blog> blogs = blogMapper.getByUserId(userId);
        for(Blog blog: blogs){
            blog.setTags(tagService.listTagOfBlog(blog.getId()));
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public Blog getById(Long id) {
        Blog blog = blogMapper.getById(id);
        blog.setTags(tagService.listTagOfBlog(blog.getId()));
        return blog;
    }

    @Override
    public Blog getBlogAndConvert(Long id) {
        Blog blog = blogMapper.getById(id);
        if(blog == null || blog.getStatus() == 0){
            throw new NotFoundException("该博客不存在");
        }
        blog.setTags(tagService.listTagOfBlog(blog.getId()));
        String content = blog.getContent();
        String new_content = MarkDownUtils.markdownToHtml(content);
        blog.setContent(new_content);
        return blog;
    }

    @Override
    public PageInfo<Blog> getOnePage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Blog> blogs = blogMapper.getAllPublic();
        for(Blog blog: blogs){
            blog.setTags(tagService.listTagOfBlog(blog.getId()));
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> getOnePagePublic(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Blog> blogs = blogMapper.getAllPublic();
        for(Blog blog: blogs){
            blog.setTags(tagService.listTagOfBlog(blog.getId()));
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public List<Blog> getOnePagePublicByType(Long typeId) { ;
        List<Blog> blogs = blogMapper.getAllPublicByTypeId(typeId);
        for(Blog blog: blogs){
            blog.setTags(tagService.listTagOfBlog(blog.getId()));
        }
        return blogs;
    }

    @Override
    public Message deleteById(Long id) {
        Message message;
        Blog blog = blogMapper.getById(id);
        if(blog == null){
            message = new Message(false, "该博客不存在！");
        }else{
            blogMapper.deleteById(id);
            batMapper.deleteBlogTagByBlogId(id);
            message = new Message(true, "删除成功！删除博客【" + blog.getTitle() +"】");
        }
        return message;
    }

    @Override
    public Message updateBlog(Blog blog) {
        Message message;
        Blog blog1 = blogMapper.getById(blog.getId());
        if(blog1 == null){
            message = new Message(false, "未查找到该博客的信息！");
            return message;
        }
        Date time = new Date();
        blog.setUpdateTime(time);
        blogMapper.update(blog);
        // 首先查找出所有更新后消失的bat数据，将他们删除，然后再插入原来不存在的bat数据(麻球烦)
        // 直接删除该博客对应的所有bat数据，再把新的插入进去就可以了。（我真是个小天才(●ˇ∀ˇ●)）
        batMapper.deleteByBlogId(blog.getId());
        List<Long> ids = Translate.getTagIds(blog.getTagIds());
        for(Long a: ids){
            batMapper.insertBAT(blog.getId(), a-48);
        }
        message = new Message(true, "博客添加成功，博客标题为：【" + blog.getTitle() + "】");
        return message;
    }

    @Override
    public PageInfo<Blog> getPublicByTypeId(Long typeId) {
        List<Blog> blogs = blogMapper.getAllPublicByTypeId(typeId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }


}
