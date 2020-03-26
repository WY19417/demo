package com.test2.demo.Controller;

import com.test2.demo.Po.UserPo;
import com.test2.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/userpo")
public class UserPoController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public String index(@RequestParam("name")String name,
                        @RequestParam("description")String description ,
                        RedirectAttributes redirectAttributes){
        UserPo userPo = userService.checkuserpo(name);
        if(userPo!=null){
            redirectAttributes.addFlashAttribute("message","用户名已经存在");
            return "redirect:/admin/user";
        }else {
           UserPo userPo1 = new UserPo();
           userPo1.setName(name);
           userPo1.setDescription(description);
           userPo1.setCreatetime(new Date());
           userService.savepo(userPo1);
            redirectAttributes.addFlashAttribute("message","新增成功");
           return "redirect:/admin/user";
        }
    }
    @GetMapping("/search")
    public String serach(@PathVariable("id")Long id,RedirectAttributes redirectAttributes){
        UserPo userPo = userService.search(id);
        redirectAttributes.addFlashAttribute("user",userPo);
        return "redirect:/admin/user";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        UserPo userPo = userService.search(id);
        model.addAttribute("users",userPo);
        return "redirect:/admin/user";
    }
    @RequestMapping("/test")
    @ResponseBody
    public UserPo test(@RequestParam("id")Long id){
        UserPo userPo =userService.search(id);
        return userPo;
    }
}
