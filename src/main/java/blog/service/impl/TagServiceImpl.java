package blog.service.impl;

import blog.entity.Tag;
import blog.entity.TagAndBlog;
import blog.handler.NotFoundException;
import blog.mapper.BATMapper;
import blog.mapper.BlogMapper;
import blog.mapper.TagMapper;
import blog.service.TagService;
import blog.vo.Message;
import blog.vo.TagCard;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BATMapper batMapper;

    @Override
    public Tag getTageById(Long id) {
        return tagMapper.getTageById(id);
    }

    @Override
    public List<Tag> listAllTag() {
        return tagMapper.listAllTag();
    }

    @Override
    public PageInfo<Tag> tagsOnePage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Tag> tags= tagMapper.listAllTag();
        if(tags == null){
            throw new NotFoundException("没有博客信息");
        }
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        return pageInfo;
    }

    @Override
    public Message insertTag(String name) {
        Tag tag1 = tagMapper.getTageByName(name);
        Message message;
        if(tag1 != null){
            message = new Message(false, "该标签名称已经存在！");
            return message;
        }
        tagMapper.saveTag(name);
        message = new Message(true, "标签添加成功，标签名称：【" + name + "】。");
        return message;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTageByName(name);
    }

    @Override
    public Message deleteById(Long id) {
        Tag tag1 = tagMapper.getTageById(id);
        Message message;
        if(tag1 == null){
            message = new Message(false, "该标签不存在！");
            return message;
        }
        tagMapper.deleteById(id);
        message = new Message(true, "标签【" + tag1.getName() + "】删除成功。");
        return message;
    }

    @Override
    public List<TagCard> allTagCard() {
        List<Tag> tags = tagMapper.listAllTag();
        List<TagCard> tagCards = new ArrayList<>();
        for(Tag tag: tags){
//            int count = tagMapper.countOfCardById(tag.getId());
            int count = batMapper.countOfTagId(tag.getId());
            TagCard tagCard = initTagCard(count, tag);
            tagCards.add(tagCard);
        }
        return tagCards;
    }

    public List<Tag> listTagOfBlog(Long id) {
        List<TagAndBlog> tagAndBlogs = batMapper.getTagOfBlogById(id);
        List<Tag> tags = new ArrayList<>();
        for(TagAndBlog tagAndBlog: tagAndBlogs){
            Tag tag = tagMapper.getTageById(tagAndBlog.getTagId());
            tags.add(tag);
        }
        return tags;
    }

    private TagCard initTagCard(int count, Tag tag){
        TagCard tagCard = new TagCard();
        tagCard.setCount(count);
        tagCard.setId(tag.getId());
        tagCard.setName(tag.getName());
        return tagCard;
    }
}
