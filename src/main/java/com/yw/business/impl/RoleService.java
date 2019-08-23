package com.yw.business.impl;

import com.github.pagehelper.PageHelper;
import com.yw.business.IRoleService;
import com.yw.dao.IRoleDao;
import com.yw.entity.Role;
import com.yw.entity.User_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("RoleService")
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> loadRoles(int page, int rows) {
        PageHelper.startPage(page,rows);
        return roleDao.loadRoles();
    }

    @Override
    public int calcMaxPage(int rows) {
        int count = roleDao.getTotalCount();
        return count%rows==0?count/rows:count/rows+1;
    }

    @Override
    public List<Role> containRole(int uid) {
        List<Role> roles = roleDao.containRole(uid);
        return roles;
    }

    @Override
    public List<Role> unContainRole(int uid) {
        List<Role> roless = roleDao.unContainRole(uid);
        return roless;
    }

    @Override
    public int queryRidByName(String roleName) {
        return roleDao.queryRidByName(roleName);
    }

    @Override
    public boolean delByUidAndRid(User_Role user_role) {
        return roleDao.delByUidAndRid(user_role)>0?true:false;
    }

    @Override
    public boolean addDistribute(User_Role user_role) {
        return roleDao.addDistribute(user_role)>0?true:false;
    }

    @Override
    public int delete(int rid) {
        return roleDao.delete(rid);
    }

    @Override
    public List<Role> loadByPid(int pid) {
        return roleDao.loadByPid(pid);
    }

}
