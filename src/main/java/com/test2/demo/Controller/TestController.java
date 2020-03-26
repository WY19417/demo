package com.test2.demo.Controller;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.apache.bcel.classfile.Unknown;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {


    @GetMapping("")
    public String sk(){
        return "/test/login";
    }
    @GetMapping("/echarts")
    @ResponseBody
    public JSONObject index(){

        String[] categories = {"鞋", "衬衫", "外套", "牛仔裤"};
        Integer[] values = {80, 50, 75, 100};
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categories",categories);
        jsonObject.put("values", values);;
        return jsonObject;
    }
    @GetMapping("/shiro")
    public String index2(Model model){
        model.addAttribute("name","程序猿");
        return "test/test";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("name","程序猿");
        return "test/add";
    }
    @GetMapping("/update")
    public String update(Model model){
        model.addAttribute("name","程序猿");
        return "test/update";
    }
    @RequestMapping("/login")
    public String login(String username,
                         String password,
                        boolean rememberMe,Model model){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);
        try {
            subject.login(token);
            return "redirect:/test/shiro";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "test/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "test/login";
        }
    }
    @RequestMapping("/uncall")
    public String uncall(){
        return "test/fail";
    }

    @RequestMapping("/tes")
    public String tes(){
        return "test/te";
    }
    @RequestMapping("/te")
    @ResponseBody
    private JSONObject te(int k){
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("message","成功");
        if (k==1){
            int val= 200;
            jsonObject.put("data",val);
        }else if (k==2){
            int val= 300;
            jsonObject.put("data",val);
        }else {
            int val= 400;
            jsonObject.put("data",val);
        }

        return jsonObject;
    }
}
