package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/param")
public class HelloController {
    @RequestMapping(path = "/hello.do")
    public String sayHello(){
        System.out.println("---- Hello ----");
        return "/hello.jsp";
    }

    @RequestMapping(value = "/param1",method = {RequestMethod.POST,RequestMethod.GET})
    public String param1(HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println("name="+name);
        return "/hello.jsp";
    }

    @RequestMapping(value = "/param2")
    public String param2(HttpServletRequest request, HttpSession session, HttpServletResponse response){
        String name = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("name="+name+", age="+age);
        session.setAttribute("name",name);
        request.setAttribute("age",age);
        return "/hello.jsp";
    }

    @RequestMapping(value = "/param3",method = {RequestMethod.GET})
    public String param3(String username, String age,HttpSession session){
        System.out.println("---- param3 ----");
        System.out.println("username="+username);
        System.out.println("age="+age);
        session.setAttribute("name",username);
        session.setAttribute("age",age);
        return "redirect:test";
    }
    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    public String test(){
        System.out.println("---- test ----");
        return "/hello.jsp";
    }



    @RequestMapping(value = "/param4",method = {RequestMethod.GET})
    public String param4(@RequestParam(value = "username",required = false) String u,
                         @RequestParam(value = "age",required = true,defaultValue = "18") String a){
        System.out.println("---- param4 ----");
        System.out.println("u="+u);
        System.out.println("a="+a);
        return "/hello.jsp";
    }



    @RequestMapping(value = "/param5",method = {RequestMethod.GET})
    public String param5(User user,HttpSession session){
        System.out.println("---- param5 ----");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        session.setAttribute("name",user.getUsername());
        session.setAttribute("bir",user.getBirthday());
        return "redirect:/param/test";
    }

    @RequestMapping(value = "param6")
    public ModelAndView hello(String username){
        System.out.println("---- hello ----");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/hello.jsp");
        modelAndView.addObject("msg","I am peter!");
        return modelAndView;
    }
}
