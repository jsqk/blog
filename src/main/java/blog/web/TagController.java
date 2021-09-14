package blog.web;

import blog.entity.Type;
import blog.service.TagService;
import blog.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    private int pageSize = 6;

    /**
     * 跳转到标签管理页面
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/tag/{page}")
    public String tagEditPage(@PathVariable int page, Model model){
        model.addAttribute("pageInfo", tagService.tagsOnePage(page, pageSize));
        return "admin/tag";
    }

    /**
     * 删除标签的处理方法
     * @param id
     * @return
     */
    @GetMapping("/tag/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes attributes){
        Message message = tagService.deleteById(id);
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else{
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/admin/tag/1";
    }

    /**
     * 添加新建标签的处理方法
     * @return
     */
    @PostMapping("/tag/new/submit")
    public String newTagPost(String name, RedirectAttributes attributes){
        Message message = tagService.insertTag(name);
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else{
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/admin/tag/1";
    }

}
