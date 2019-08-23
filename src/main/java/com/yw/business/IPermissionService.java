package com.yw.business;

import com.yw.entity.Permission;
import com.yw.entity.Role_Permission;

import java.util.List;

public interface IPermissionService {
    //加载所有的权限信息
    public List<Permission> loadAll();
    //根据roleId查询角色所拥有的所有权限
    public List<Permission> loadByRoleId(int roleId);
    //通过pname获取对应的主键id值
    public int getIdByName(String pname);
    //添加新的p-r关系表的数据
    public int add(Role_Permission rolePermission);
}
