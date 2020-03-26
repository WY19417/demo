package com.test2.demo.Controller;

import com.test2.demo.Po.User;
import com.test2.demo.Service.UserService;
import com.test2.demo.util.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sys")
public class Randompictures {
    private final static Logger logger = LoggerFactory.getLogger(Randompictures.class);

    @Autowired
    private UserService userService;


    @GetMapping("")
    public String index(HttpSession session){
        session.setAttribute("msg","111");
        return "test/testrandom";
    }

    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
            System.out.println("获取验证码成功");
            session.setAttribute("msg","111");
        } catch (Exception e) {
            System.out.println("获取验证码失败>>>> ");
        }
    }
    /*
    检查验证码是否正确
     */
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean checkVerify(String verifyInput,HttpSession session) {
        try{
            //从session中获取随机数
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                System.out.println("验证码为空");
                return false;
            }
            if (random.equals(verifyInput)) {
                System.out.println("验证----验证码成功");
                return true;
            } else {
                System.out.println("验证码不正确");
                return false;
            }
        }catch (Exception e){
            logger.error("验证-获取验证码失败>>>> ",e);
            System.out.println("获取验证码失败>>>> ");
            return false;
        }
    }
}
