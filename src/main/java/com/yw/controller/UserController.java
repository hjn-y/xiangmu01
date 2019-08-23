package com.yw.controller;

import com.yw.business.IUserService;
import com.yw.business.impl.UserService;
import com.yw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;


    @RequestMapping(value="/loginhao",method = RequestMethod.POST)
    public String loginhao(User user){

        User u = null;
        try {
            u = userService.loadUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(u == null || !user.getUserPwd().equals(u.getUserPwd())){
            return "/login";
        }
        if("会员".equals(u.getRoleName())){
            return "/member";
        }
        if("管理员".equals(u.getRoleName())){
            return "/main";
        }
        return "/login";

    }
   /* @RequestMapping(value = "/getusers",method = RequestMethod.GET)
    public String getusers(Integer roleId,Model model){
        System.out.println("roleId:");
        List<User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "/user";
    }*/

    @RequestMapping(value = "/loadUsers")
    public String loadUsers(@RequestParam(required = false,defaultValue = "1")int page,
                            @RequestParam(required = false,defaultValue = "6")int rows,
                            Model model){
        int maxPage = userService.calcMaxPage(rows);
        if(page<1){
            page=maxPage;
        }if (page>maxPage){
            page=1;
        }
        List<User> users = userService.loadUsers(page, rows);
        model.addAttribute("users",users);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "user";
    }
    @RequestMapping("/addNewUser")
    public String addNewUser(User user){
        boolean b = userService.addNewUser(user);

        return b?"add":"user";
    }

    @ResponseBody
    @RequestMapping("/addResult")
    public boolean addResult(String userName,String userPwd,String userEmail){
        User user=new User();
        user.setUserPost("zhi");
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        user.setUserEmail(userEmail);
        boolean b= userService.addNewUser(user);
        return b;
    }
    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(int userId){
        boolean b = userService.deleteById(userId);

        return b;
    }

    private int upUserId=0;
    @RequestMapping("/edit")
    public String edit(int aid,Model model){
        upUserId=aid;
        User user = userService.loadById(aid);
        model.addAttribute("a",user);
        return "edit";
    }
    @ResponseBody
    @RequestMapping("/alter")
    public boolean alter(String userName,String userPost,String userEmail){
        User user = userService.loadById(upUserId);
        user.setUserName(userName);
        user.setUserPost(userPost);
        user.setUserEmail(userEmail);
        boolean bool = userService.upUser(user);
        System.out.println(bool);
        return bool;
    }
    @ResponseBody
    @RequestMapping("/delMuch")
    public boolean delMuch(String sc){
        boolean a=false;
        String[] ids=sc.split(",");
        for (int i=0;i<ids.length;i++){
            if (!ids[i].equals("on")){
                a=userService.deleteById(Integer.parseInt(ids[i]));
            }
        }
        return a;
    }
    @RequestMapping("/zc")
    public String zc(User user,Model model){
        boolean b = userService.addNewUser(user);
        model.addAttribute("user",user);
        return b?"reg":"重新输入";
    }
    @RequestMapping("/dim")
    //模糊查询页面
    public String dim(String cx,Model model){
        List<User> usersx=userService.queryMh(cx);
        if (!cx.equals(null)){

            model.addAttribute("usersx",usersx);

        }else {
            model.addAttribute("usersx", null);
        }
        return "dim";
    }
}
