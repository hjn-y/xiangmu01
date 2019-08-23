package com.yw.controller;

import com.yw.business.impl.PermissionService;
import com.yw.business.impl.RoleService;
import com.yw.entity.Permission;
import com.yw.entity.Role_Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {
    int currentId=0;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/assignPermission")
    //加载当前角色id获取到页面
    public String assignPermission(int rid){
        currentId=rid;
        return "assignPermission";
    }

    @ResponseBody
    @RequestMapping("/assignPermissionx")
    //根据id查到对应角色的权限
    public List<Permission> loadPermission(){
        //加载出所有的权限信息
        List<Permission> allPermission = permissionService.loadAll();
        //加载当前角色所有权限信息
        List<Permission> rolePermission = permissionService.loadByRoleId(currentId);
        for (Permission p:rolePermission){
            for (Permission x:allPermission){
                if (p.getId()==x.getId()) {
                    x.setChecked("true");
                    break;
                }
            }
        }
        return allPermission;
    }


    @ResponseBody
    @RequestMapping("/loadAllPermission")
    //获取所有的权限
    public List<Permission> permissionManager(){
        List<Permission> allPermission=permissionService.loadAll();
        return allPermission;
    }

    @ResponseBody
    @RequestMapping("/allocationPermissionOk")
    //判断是否重新分配权限成功,把新的权限分配到数据库中
    public boolean allocationPermissionOk(String pids){
        String[] ps = pids.split("-");
        int state = roleService.delete(currentId);
        int s1=0;
        int s2=0;
        Role_Permission role_permission=null;
        for (int i=0;i<ps.length;i++){
            role_permission=new Role_Permission();
            role_permission.setCurrentId(currentId);
            role_permission.setI(ps[i]);
            s1=permissionService.add(role_permission);
            if (s1>0){
                s2+=1;
            }
        }
        return s2>0?true:false;
    }
}
