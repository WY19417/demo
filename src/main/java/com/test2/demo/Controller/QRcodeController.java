package com.test2.demo.Controller;

import com.test2.demo.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
@Controller
@RequestMapping("/code")
public class QRcodeController {

    @RequestMapping(value = "/zx")
    @ResponseBody
    public String selectALLa(Model model) throws Exception {
        // 存放在二维码中的内容
        String text = "2121";
        // 二维码中嵌入图片的存放路径和名称
         String imgPath = "E:/images/sss.jpg";
         //二维码生成的路径及名称
         String destPath = "E:/images/sss.jpg";
        // 执行生成二维码
         QRCodeUtil.encode(text, imgPath, destPath, true);
         // 解析二维码存放内容
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
         //返回前台页面显示二维码
        model.addAttribute("img",destPath);
        return  "test/QRcode.html";
    }
    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    // 跳转上传页面
    @RequestMapping("test")
    public String test() {
        return "test/QRcode.html";
    }

    // 执行上传
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String path = filePath+"rotPhoto/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将src路径发送至html页面
        model.addAttribute("filename", "/images/rotPhoto/"+filename);
        return "test/QRcode.html";
    }


}
