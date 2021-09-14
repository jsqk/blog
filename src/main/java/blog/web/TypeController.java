package blog.web;

import blog.entity.Type;
import blog.service.TypeService;
import blog.vo.Message;
import blog.vo.TypeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    private int pageSize = 6;

    /**
     * 跳转到分类管理页面
     * @return
     */
    @GetMapping("/type/{page}")
    public String typeManagePage(Model model, @PathVariable int page){
        model.addAttribute("pageInfo", typeService.listOnePageType(page, pageSize));
        return "admin/type";
    }

    /**
     * 跳转到新建分类页面
     * @return
     */
    @GetMapping("/type/new")
    public String newTypePage(){
        return "type-edit";
    }

    /**
     * 添加新建分类的处理方法
     * @return
     */
    @PostMapping("/type/new/submit")
    public String newTypePost(Type type, RedirectAttributes attributes){
        if(typeService.saveType(type)){
            attributes.addFlashAttribute("success", "分类添加成功。");
        }else{
            attributes.addFlashAttribute("fail", "分类不能出现重名，【" + type.getName() + "】已经存在。");
        }
        return "redirect:/admin/type/1";
    }

    /**
     * 跳转到分类编辑的页面，需要传入一个分类id
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/type/{id}/edit")
    public String editType(Model model, @PathVariable Long id){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type", type);
        return "admin/type-edit";
    }

    /**
     * 修改分类的处理方法
     * @param attributes
     * @param type
     * @return
     */
    @PostMapping("/type/edit/submit")
    public String editTypePost(RedirectAttributes attributes, Type type){
//        System.out.println(type.toString());
        Message message = typeService.updateType(type);
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else{
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/admin/type/1";
    }

    /**
     * 删除分类的处理方法
     * @param id
     * @return
     */
    @GetMapping("/type/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes attributes){
        Message message = typeService.deleteTypeById(id);
        if(message.isFlag()){
            attributes.addFlashAttribute("success", "分类【" + message.getMessage() + "】已被删除！");
        }else{
            attributes.addFlashAttribute("fail", "删除失败，未找到该分类！");
        }
        return "redirect:/admin/type/1";
    }

}

