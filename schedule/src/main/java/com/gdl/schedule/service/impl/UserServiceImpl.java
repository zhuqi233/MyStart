package com.gdl.schedule.service.impl;

import com.gdl.schedule.entity.User;
import com.gdl.schedule.mapper.UserMapper;
import com.gdl.schedule.service.UserService;
import com.gdl.schedule.service.ex.PasswordNotMatchException;
import com.gdl.schedule.service.ex.UsernameNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username,String password) {
        // 根据用户名获取用户数据
        User u = userMapper.findUserByUserName(username);
        // 判断是否获取到用户数据
        if (u != null) {
            // 数据不为null，即获取到了数据
            // 即用户名存在
            // 则判断密码
            if (u.getPassword().equals(password)) {
                // 密码匹配，则登录成功
                return u;
            } else {
                // 密码不匹配，则登录失败
                // 则抛出业务异常
                throw new PasswordNotMatchException("密码错误");
            }
        } else {
            // 数据为null，即根据用户名查询不到有效数据
            // 即用户名不存在
            // 则抛出业务异常
            throw new UsernameNotFoundException("用户名不存在");
        }
    }

    @Override
    public User findUserByID(Integer id) {
        return userMapper.findUserByID(id);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }
}
