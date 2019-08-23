package com.yw.dao;
import com.yw.entity.User;

import java.util.List;

public interface IUserDao {
    //根据当前登录用户信息登录页面
    public User loadUser(User user);
    //加载所有用户
    public List<User> loadUsers();
    //获取用户总数
    public int getTotalCount();
    //添加新用户
    public int addNewUser(User user);
    //获取最大id
    public int getMaxId();
    //删除当前用户
    public int deleteById(int userId);
    //根据id获取当前的用户信息
    public User loadById(int userId);
    //修改用户信息
    public int upUser(User user);
    //根据userName模糊查询
    public List<User> queryByUserName(String cx);
    //根据userPost模糊查询
    public List<User> queryByUserPost(String cx);
    //模糊查询
    public List<User> queryMh(String cx);
}
