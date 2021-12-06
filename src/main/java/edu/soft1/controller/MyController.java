package edu.soft1.controller;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@Controller
@RequestMapping(value = "/param1")
public class MyController {

    //头像上传
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile[] images, HttpServletRequest request) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        for (MultipartFile image:images) {
            is = image.getInputStream();
            String filename = image.getOriginalFilename();
            System.out.println("文件原名称"+filename);
            if (filename.equals("")){
                System.out.println("空文件上传");
                continue;
            }
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径=" + realPath);
            os = new FileOutputStream(new File(realPath, doFilename(filename)));
            int size = IOUtils.copy(is, os);
            System.out.println("完成上传size=" + size + "Byte");

            System.out.println("---- upload ----");
        }
        os.close();
        is.close();
        return "welcome";
    }


    private String doFilename(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件后最名
        String uuid = UUID.randomUUID().toString();
        System.out.println("上传文件名"+uuid);
        return uuid+"."+extension;
    }

    @RequestMapping(value = "/use")
    public String use(){
        System.out.println("---- use ----");
        return "hello";
    }
}
