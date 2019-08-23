package com.yw.business.impl;

import com.github.pagehelper.PageHelper;
import com.yw.business.IUserService;
import com.yw.dao.IUserDao;
import com.yw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User loadUser(User user) {
        User user1 = userDao.loadUser(user);
        System.out.println(user1);
        return user1;
    }

    @Override
    public List<User> loadUsers(int page, int rows) {
        PageHelper.startPage(page,rows);
        return userDao.loadUsers();
    }

    @Override
    public int calcMaxPage(int rows) {
        int count = userDao.getTotalCount();
        return count%rows==0?count/rows:count/rows+1;
    }

    @Override
    public boolean addNewUser(User user) {
        user.setUserId(userDao.getMaxId()+1);
        return userDao.addNewUser(user)>0?true:false;
    }

    @Override
    public boolean deleteById(int userId) {
        return userDao.deleteById(userId)>0?true:false;
    }

    @Override
    public User loadById(int userId) {
        User user = userDao.loadById(userId);
        return user;
    }

    @Override
    public boolean upUser(User user) {
        return userDao.upUser(user)>0?true:false;
    }

    @Override
    public List<User> queryMh(String cx) {
        List<User> users = userDao.queryMh(cx);

        return users;
    }

    @Override
    public List<User> dimQuery(String cx) {
        List<User> users=new ArrayList<>();
        List<User> users1 = userDao.queryByUserName(cx);
        if(users1!=null){
            for (User u:users1){
                users.add(u);
            }
        }
        List<User> users2 = userDao.queryByUserPost(cx);
        if(users2!=null){
            for (User u:users2){
                users.add(u);
            }
        }

        return users;
    }



}
