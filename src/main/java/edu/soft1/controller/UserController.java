package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "user")
public class UserController {


    //测试
    @RequestMapping(value = "/param1",method = {RequestMethod.GET})
    public String param1(User user, HttpSession session){
        System.out.println("---- param1 ----");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        session.setAttribute("name",user.getUsername());
        session.setAttribute("bir",user.getBirthday());
        return "redirect:/user/test";
    }


    @RequestMapping(value = "test")
    public String test(){
        System.out.println("---- test ----");
        return "/hello.jsp";
    }



    //登录
    @RequestMapping(value = "logon",method = {RequestMethod.POST})
    public String logon(User user, HttpSession session, HttpServletRequest request){
        System.out.println("---- logon ----");
        int flag = 1;
        if (flag ==1 ){
            session.setAttribute("user",user);
            System.out.println("user="+user == null);
            System.out.println("username="+user.getUsername());
            System.out.println("password="+user.getPassword());
            return "welcome";
        }
        System.out.println("登录失败");
        request.setAttribute("error","登录失败！");
       return "forward:/index.jsp";
    }


    //执行删除功能
    @RequestMapping(value = "delete")
    public String delete(){
        System.out.println("---- delete ----");
        return "welcome";
    }


    //登出
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        System.out.println("已经登出");
//        System.out.println(session == null);
//        User user = (User) session.getAttribute("user");
//        System.out.println(user.getUsername());
        return "redirect:/index.jsp";
    }
}
