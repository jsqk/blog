package blog.web;

import blog.entity.Blog;
import blog.entity.User;
import blog.service.BlogService;
import blog.service.TagService;
import blog.service.TypeService;
import blog.util.FileUtil;
import blog.util.Setting;
import blog.vo.Message;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    private int pageSize = 6;


    /**
     * 跳转到博客管理页面
     * @param page
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/blog/{page}")
    public String listBlogOfUser(@PathVariable int page, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("tags", tagService.listAllTag());
        model.addAttribute("types", typeService.listAllType());
        model.addAttribute("pageInfo", blogService.getOnePageByUser(user.getId(), page, pageSize));
        return "admin/blog";
    }

    /**
     * 新建博客的处理方法
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blog/new/submit")
    public String saveBlogPost(Blog blog, RedirectAttributes attributes, HttpSession session){
//        System.out.println(blog.toString());
        User user = (User) session.getAttribute("user");
        if(user == null){
            Message message = new Message(false, "没有博客的用户信息，请先登录！");
            attributes.addFlashAttribute("success", message.getMessage());
            return "redirect:/admin/blog/1";
        }
        Message message = blogService.saveBlog(blog, user.getId());
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else {
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/admin/blog/1";
    }

    /**
     * 删除博客的处理方法
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blog/delete/{id}")
    public String deleteBlogPost(@PathVariable Long id, RedirectAttributes attributes){
        Message message = blogService.deleteById(id);
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else {
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/admin/blog/1";
    }

    /**
     * 跳转到编辑博客的页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}/edit")
    public String toBlogEditPage(Model model, @PathVariable Long id){
        model.addAttribute("blog", blogService.getById(id));
        model.addAttribute("types", typeService.listAllType());
        model.addAttribute("tags", tagService.listAllTag());
        return "admin/blogEdit";
    }

    @PostMapping("/blog/edit/post")
    public String updateBlogPost(Blog blog, RedirectAttributes attributes){
        Message message = blogService.updateBlog(blog);
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else {
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/admin/blog/1";
    }

    private FileUtil fileUtil = new FileUtil();

    @RequestMapping("/blog/editormdPicUpload")
    @ResponseBody
    public JSONObject editormdPicUpload(@RequestParam(value = "editormd-image-file",required = false)MultipartFile image) throws IOException{
        JSONObject jsonObject=new JSONObject();
        try{
            String url = fileUtil.save(image, Setting.BLOG_IMG_PATH, image.getOriginalFilename());
            jsonObject.put("success",1);
            jsonObject.put("message","上传成功~");
            jsonObject.put("url",url);

        }
        catch (Exception e){
            jsonObject.put("success",0);
            jsonObject.put("message","出错啦~");
        }
        return jsonObject;
    }
}
