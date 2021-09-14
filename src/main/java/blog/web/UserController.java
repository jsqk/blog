package blog.web;

import blog.entity.User;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录的处理方法
     */
    @PostMapping("/submit")
    public String loginPost(String username, String password, HttpSession session, RedirectAttributes attributes){
        if(userService.loginCheck(username, password)){
            User user = userService.getUserByUserName(username);
            session.setAttribute("user", user);
            return "redirect:/admin/blog/1";
        }else{
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logoutPost(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
