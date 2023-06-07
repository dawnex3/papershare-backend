package com.itheima.service.User;

import com.itheima.mapper.User.UserMapper;
import com.itheima.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addOneUser(User user) throws Exception{
        userMapper.addOneUser(user);
    }
    @Override
    public void deleteOneUserById(Integer userId) throws Exception{
        userMapper.deleteOneUserById(userId);
    }
    @Override
    public void updateOneUser(User user) throws Exception{
        userMapper.updateOneUser(user);
    }
    @Override
    public User getOneUserByUserId(Integer userId) throws Exception{
        return userMapper.getOneUserByUserId(userId);
    }
    @Override
    public List<User> getAllUser() throws Exception{
        return userMapper.getAllUser();
    }
    @Override
    public String getPassword(Integer user_id) throws Exception{
        return userMapper.getPassword(user_id);
    }
}
