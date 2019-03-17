package com.gdl.schedule.service;

import com.gdl.schedule.entity.User;

public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return  User对象
     */
    public User login(String username,String password);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return  User对象
     */
    public User findUserByID(Integer id);

    /**
     * 新增用户
     * @param user
     * @return  新增用户id
     */
    public Integer insertUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return  修改的数据数量
     */
    public Integer updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return  删除的数据数量
     */
    public Integer deleteUser(Integer id);
}
