package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    /**
     * 测试多文件上传
     * @param username
     * @param file
     * @param model
     * @return
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "username",required = false) String username, @RequestParam("headerimg")MultipartFile[] file, Model model) {

        System.out.println("上传的文件的信息");

        for (MultipartFile multipartFile : file) {
            if (!multipartFile.isEmpty()){
                //文件保存
                try {
                    multipartFile.transferTo(new File("C:\\Users\\niexiaohan\\Desktop\\FileUpload\\"+multipartFile.getOriginalFilename()));
                    model.addAttribute("msg", "文件上传成功！");
                } catch (Exception e) {
                    model.addAttribute("msg", "文件上传失败了！"+e.getMessage());
                }
            }
        }

        return "forward:/index.jsp";
    }

//    @RequestMapping("/upload")
//    public String upload(@RequestParam(value = "username",required = false) String username, @RequestParam("headerimg")MultipartFile file, Model model) {
//
//        System.out.println("上传的文件的信息");
//        System.out.println("文件项的name："+file.getName());
//        System.out.println("文件的名字："+file.getOriginalFilename());
//
//        //文件保存
//        try {
//            file.transferTo(new File("C:\\Users\\niexiaohan\\Desktop\\FileUpload\\"+file.getOriginalFilename()));
//            model.addAttribute("msg", "文件上传成功！");
//        } catch (Exception e) {
//            model.addAttribute("msg", "文件上传失败了！"+e.getMessage());
//        }
//
//        return "forward:/index.jsp";
//    }
}
