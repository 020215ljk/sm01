package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/json")
public class JsonController {

    @RequestMapping(value = "getUser", method = RequestMethod.POST)
    @ResponseBody//1. 返回结果不会被解析为跳转路径，而是直接写如HTTP响应正文中；
                 //2.返回的单个java对象会背转换为单个json对象
    public User test1(){
        User user = new User();
        user.setUsername("peter");
        user.setBirthday(new Date());
        return user;
    }

    @RequestMapping(value = "setUser")
    @ResponseBody
    public User test11(@RequestBody User user){
        System.out.println("接受到client的数据="+user);
        return user;
    }
}
