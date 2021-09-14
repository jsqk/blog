package blog.web;

import blog.entity.Blog;
import blog.entity.LeaveWord;
import blog.service.*;
import blog.vo.Message;
import blog.vo.TagCard;
import blog.vo.TypeCard;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/myblog")
public class NormalController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TimeLineService timeLineService;
    @Autowired
    private LWService lwService;

    private int pageSize = 6;

    /**
     * 跳转到博客分类页面
     * @param model
     * @return
     */
    @GetMapping("/typeCard")
    public String typeInfoOfBlog(Model model){
        List<TypeCard> listTypeCard = typeService.listTypeCard();
        model.addAttribute("typeCards", listTypeCard);
        return "blog/typecard";
    };

    /**
     * 跳转到博客标签页面
     * @param model
     * @return
     */
    @GetMapping("/tagBlog")
    public String tagInfoOfBlog(Model model){
        List<TagCard> tagCards = tagService.allTagCard();
        model.addAttribute("tagCards", tagCards);
        return "blog/tagblog";
    };

    /**
     * 跳转到留言页面
     * @param model
     * @return
     */
    @GetMapping("/leaveword")
    public String toLWPage(Model model){
        List<LeaveWord> pageInfo = lwService.getOnePage(1, pageSize);
        model.addAttribute("leavewords", pageInfo);
        return "blog/leavewords";
    }


    /**
     * 跳转到某一分类的博客列表页面
     * @param typeId
     * @param model
     * @return
     */
    @GetMapping("/blog/type={typeId}")
    public String blogOfOneType(@PathVariable Long typeId, Model model){
        Message message = typeService.getNameById(typeId);
        if(!message.isFlag()){
            //跳转到错误页面
        }
        model.addAttribute("name", message.getMessage());
        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.getOnePagePublicByType(typeId));
        List<Blog> blogs = blogService.getOnePagePublicByType(typeId);
        model.addAttribute("blogs", blogs);
        return "blog/bloglist";
    }


    /**
     * 跳转到某一个博客的详情页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}/details")
    public String blogDetailsPage(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogAndConvert(id);
        model.addAttribute("blog", blog);
        return "blog/details";
    }

    /**
     * 跳转到关于我页面
     * @return
     */
    @GetMapping("/about")
    public String aboutMe(){
        return "blog/about";
    }

    /**
     * 跳转到网站首页
     * @return
     */
    @GetMapping("/home")
    public String homePage(){
        return "blog/home";
    }

    /**
     * 跳转到博客首页
     * @return
     */
    @GetMapping("/bloglist")
    public String blogHomePage(Model model){
        PageInfo<Blog> pageInfo = blogService.getOnePagePublic(1, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("count", blogService.countOfPublic());
        List<TypeCard> listTypeCard = typeService.listTypeCard();
        model.addAttribute("typeCards", listTypeCard);
        return "blog/bloglist";
    }

    /**
     * 博客首页页面跳转
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/blog/{page}")
    public String blogListPost(Model model, @PathVariable int page){
        PageInfo<Blog> pageInfo = blogService.getOnePagePublic(page, pageSize);
        model.addAttribute("count", blogService.countOfPublic());
        model.addAttribute("pageInfo", pageInfo);
        return "blog/bloglist::bloglist";
    }

    @GetMapping("/type/{id}")
    public String blogTypePost(Model model, @PathVariable Long id){
        PageInfo<Blog> pageInfo = blogService.getPublicByTypeId(id);
        model.addAttribute("count", blogService.countOfPublic());
        model.addAttribute("pageInfo", pageInfo);
        return "blog/bloglist::bloglist";
    }

    /**
     * 跳转到‘时光’页面
     * @param model
     * @return
     */
    @GetMapping("/timeline")
    public String timelinePage(Model model){
        model.addAttribute("timeinfo",timeLineService.getAllTimeLine());
        return "blog/timeline";
    }
}
