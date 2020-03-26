package com.test2.demo.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.test2.demo.Po.User;
import com.test2.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Randompictures randompictures;
    /*
    登录管理系统界面
     */
    @GetMapping("")
    public String index(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
    @GetMapping("/juese")
    public String index2(){
        return "zuzhi/juese";
    }
    @GetMapping("/bumen")
    public String index3(){
        return "zuzhi/bumen";
    }
    @GetMapping("/user")
    public String index4(@PageableDefault(size = 5,sort = {"createTime"},direction = Sort.Direction.ASC)
                                     Pageable pageable, Model model){
        model.addAttribute("user",userService.findAll(pageable));
        return "zuzhi/user";
    }
    @PostMapping("/user/search")
    public String search(@PageableDefault(size = 5,sort = {"createTime"},direction = Sort.Direction.ASC)
                                 Pageable pageable, Model model){
        model.addAttribute("user",userService.findAll(pageable));
        return "zuzhi/user";
    }
    @GetMapping("/menu")
    public String index5(){
        return "zuzhi/menu";
    }
    /*
    验证用户名,密码及验证码是否正确
     */
    @PostMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam("verifyInput")String verifyInput,
                        Model model, RedirectAttributes  redirectAttributes,
                        HttpSession session){
        boolean rad = randompictures.checkVerify(verifyInput,session);
        User user = userService.checkuserandpass(username,password);
        if (user !=null){
                if(rad){
                    model.addAttribute("user",user);
                    session.setAttribute("user",user);
                    return "index";
                }else {
                    redirectAttributes.addFlashAttribute("message","验证码错误");
                    return "redirect:/admin";
                }
        }else{
            redirectAttributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/admin";
        }

    }

    @PostMapping("/search")
    private String index2(RedirectAttributes  redirectAttributes){
        System.out.println(userService.select((long) 1));
        redirectAttributes.addFlashAttribute("user",userService.select((long) 1));
        return "redirect:/";
    }
    @PostMapping("/add/user")
    public String index4(@RequestParam("username")String username,
                         @RequestParam("password")String password,
                         @RequestParam("phone")String phone,
                         @RequestParam("mail")String mail,
                         Model model,RedirectAttributes redirectAttributes){
        User user = userService.checkuser(username);
        if (user!=null){
            redirectAttributes.addFlashAttribute("message","用户名重复");
            return "redirect:/add";
        }else{
            User user1 = new User();
            user1.setUsername(username);
            user1.setPassword(password);
            user1.setPhone(phone);
            user1.setMail(mail);
            userService.save(user1);
            model.addAttribute("message","用户注册成功");
            return "login";
        }
    }
}
