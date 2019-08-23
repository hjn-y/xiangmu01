package com.yw.business;

import com.yw.entity.User;

import java.util.List;

public interface IUserService {
    //根据当前登录用户信息登录
   public User loadUser(User user);
    //根据页数和行数加载该页面用户
   public List<User> loadUsers(int page,int rows);
    //获取最大页数
   public int calcMaxPage(int rows);
    //添加新用户
   public boolean addNewUser(User user);
    //根据id删除当前用户
   public boolean deleteById(int userId);
   //根据id查询当前用户信息
    public User loadById(int userId);
    //修改用户
    public boolean upUser(User user);
    //模糊查询
    public List<User> queryMh(String cx);
    //模糊查询结果集
    public List<User> dimQuery(String cx);
}
