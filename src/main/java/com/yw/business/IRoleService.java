package com.yw.business;

import com.yw.entity.Role;
import com.yw.entity.User_Role;

import java.util.List;

public interface IRoleService {
    //加载所有角色
    public List<Role> loadRoles(int page,int rows);
    //获得最大的页数
    public int calcMaxPage(int rows);
    //根据uid查出含有的角色有哪些
    public List<Role> containRole(int uid);
    //查不含有
    public List<Role> unContainRole(int uid);
    //根据名字查询id
    public int queryRidByName(String roleName);
    //根据uid和rid删除对于的中间表信息
    public boolean delByUidAndRid(User_Role user_role);
    //将新的用户角色分配信息存入到关系表中
    public boolean addDistribute(User_Role user_role);
    //根据rid删除role_permission表中的数据
    public int delete(int rid);
    //根据权限id查出所有角色
    public List<Role> loadByPid(int pid);
}
