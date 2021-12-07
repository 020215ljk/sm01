package edu.soft1.controller;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/param1")
public class MyController {

    //上传图片
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile[] images, HttpServletRequest request) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        int count = 0;
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
            if (size > 0){
                count++;
            }
        }
        os.close();
        is.close();
        Map map = null;
        map.put("msg2",count);
        System.out.println("上传成功"+count+"次");
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


    //下载图片
    @RequestMapping(value="/load.do/{fileName}",method={RequestMethod.GET})
    public void load(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        //设置文件下载
        response.setHeader("Content-Disposition","attachment;filename="+doFileName2(request, fileName));
        //文件存储的真实位置
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("下载路径realPath="+realPath);
        //根据存储的文件，获取输入流对象
        InputStream is = new FileInputStream(new File(realPath, fileName));
        //根据相应对象获取输出流对象
        OutputStream os = response.getOutputStream();
        //把输入流写入输出流
        IOUtils.copy(is, os);
        //释放资源，原则：先开后关，后开先关
        os.close();
        is.close();
    }
    //针对中文名称，需要分浏览器来处理
    public String doFileName2(HttpServletRequest request, String filename){
        try{
            //获取请求头部信息的User-Agent对应的值
            String userAgent=request.getHeader("User-Agent");
            if(userAgent.toUpperCase().indexOf("FIREFOX")>0){//火狐浏览器
                filename= "=?UTF-8?B?"+(new String(Base64.encodeBase64(filename.getBytes("utf-8"))))+"?=";
            }else{//其他浏览器
                filename  = URLEncoder.encode(filename,"utf-8");
            }
            System.out.println("下载文件名="+filename);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filename;
    }



    //头像上传（单张）
    @RequestMapping(value = "/headUpload",method = {RequestMethod.POST})
    public String headUpload(MultipartFile image, HttpServletRequest request) throws IOException {
        Map map = null;
        InputStream is = image.getInputStream();
        String filename = image.getOriginalFilename();
        System.out.println("文件原名称"+filename);
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径=" + realPath);
        OutputStream os = new FileOutputStream(new File(realPath, doFilename(filename)));
        int size = IOUtils.copy(is, os);
        System.out.println("完成上传size=" + size + "Byte");
        System.out.println("---- upload ----");
        os.close();
        is.close();
        if (size > 0){
            map.put("msg","uploadSuccess");
        }
        System.out.println("上传成功");
        return "welcome";
    }
}
