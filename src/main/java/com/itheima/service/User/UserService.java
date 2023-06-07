package com.itheima.service.User;

import com.itheima.model.User.User;

import java.util.List;

public interface UserService {
    void addOneUser(User user) throws Exception;
    void deleteOneUserById(Integer userId) throws Exception;
    void updateOneUser(User user) throws Exception;
    User  getOneUserByUserId(Integer userId) throws Exception;
    List<User> getAllUser() throws Exception;
    String getPassword(Integer user_id) throws Exception;
}
