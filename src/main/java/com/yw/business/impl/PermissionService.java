package com.yw.business.impl;

import com.yw.business.IPermissionService;
import com.yw.dao.IPermissionDao;
import com.yw.entity.Permission;
import com.yw.entity.Role_Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> loadAll() {
        return permissionDao.loadAll();
    }

    @Override
    public List<Permission> loadByRoleId(int roleId) {
        return permissionDao.loadByRoleId(roleId);
    }

    @Override
    public int getIdByName(String pname) {
        return permissionDao.getIdByName(pname);
    }

    @Override
    public int add(Role_Permission rolePermission) {
        return permissionDao.add(rolePermission);
    }
}
