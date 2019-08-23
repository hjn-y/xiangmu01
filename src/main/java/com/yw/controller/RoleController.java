package com.yw.controller;

import com.yw.business.IRoleService;
import com.yw.business.impl.PermissionService;
import com.yw.business.impl.UserService;
import com.yw.entity.Permission;
import com.yw.entity.Role;
import com.yw.entity.User;
import com.yw.entity.User_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/loadRoles")
    public String loadRoles(@RequestParam(required = false,defaultValue = "1")int page,
                            @RequestParam(required = false,defaultValue = "5")int rows,
                            Model model){
        int maxPage = roleService.calcMaxPage(rows);
        if (page<1){
            page=maxPage;
        }if (page>maxPage){
            page=1;
        }
        List<Role> roles = roleService.loadRoles(page, rows);
        model.addAttribute("roles",roles);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "role";
    }

    private int curUid;//定义一个值接收当前要修改角色的用户id
    @RequestMapping("/assignRole")//通过用户id加载其所担任的所有角色
    public String assignRole(int uid,Model model){
        curUid=uid;
        System.out.println(uid);
        List<Role> containRoles= roleService.containRole(uid);
        System.out.println(containRoles);
        List<Role> unContainRoles = roleService.unContainRole(uid);
        System.out.println(unContainRoles);
        model.addAttribute("containRoles",containRoles);
        model.addAttribute("unContainRoles",unContainRoles);
        return "assignRole";
    }
    @ResponseBody
    @RequestMapping("/roleDistributeReduce")
    public boolean roleDistributeReduce(String opss){
        String[] opArray=opss.split("_"); //当前想要删除的角色
        System.out.println(opArray);
        User_Role user_role=null;//定义一个关系类对象接收要删除的信息
        int s=0;//定义一个参数判断存储是否成功
        //将新的角色分配信息存储到关系表中
        for (int i=0;i<opArray.length;i++){
            user_role=new User_Role();
            user_role.setUid(curUid);
            user_role.setRid(roleService.queryRidByName(opArray[i]));
            System.out.println(user_role);
            boolean a = roleService.delByUidAndRid(user_role);
            System.out.println(a);
            if(a){
                s++;
            }
        }
        return s>0?true:false;
    }
    @ResponseBody
    @RequestMapping("/roleDistributeAdd")
    public boolean roleDistributeAdd(String opss){
        String[] opArray=opss.split("_");
        User_Role user_role=null;
        int s=0;
        for (int i=0;i<opArray.length;i++){
            user_role=new User_Role();
            user_role.setUid(curUid);
            user_role.setRid(roleService.queryRidByName(opArray[i]));
            boolean a = roleService.addDistribute(user_role);
            if(a){
                s++;
            }
        }
        return s>0?true:false;
    }

    @RequestMapping("/addRole")
    //新增角色页面下拉列表的全部权限选项
    public String addRole(Model model){
        List<Permission> permissions=permissionService.loadAll();
        model.addAttribute("permissions",permissions);
        return "addRole";
    }

}
