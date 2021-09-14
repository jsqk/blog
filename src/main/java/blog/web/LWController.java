package blog.web;

import blog.entity.LeaveWord;
import blog.service.LWService;
import blog.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Author: hanzy
 * Date: 2021/9/13, 21:22
 * introduce:
 */
@Controller
public class LWController {
    @Autowired
    private LWService lwService;

    @PostMapping("/leavewordpost")
    public String lwPost(LeaveWord leaveWord, RedirectAttributes attributes){
        Message message =
        lwService.save(leaveWord);
//        System.out.println(leaveWord.toString());
        if(message.isFlag()){
            attributes.addFlashAttribute("success", message.getMessage());
        }else {
            attributes.addFlashAttribute("fail", message.getMessage());
        }
        return "redirect:/myblog/leaveword";
    }

}
