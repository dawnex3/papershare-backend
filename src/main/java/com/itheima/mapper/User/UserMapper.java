package com.itheima.mapper.User;


import com.itheima.model.User.User;

import java.util.List;

public interface UserMapper {
    // 添加一个用户
    void addOneUser(User users) throws Exception;
    // 删除一个用户
    void deleteOneUserById(Integer userId) throws Exception;
    // 修改一个用户的信息
    void updateOneUser(User user) throws Exception;
    // 根据主键id获取一位用户
    User getOneUserByUserId(Integer userId) throws Exception;
    // 获取全部用户
    List<User> getAllUser() throws Exception;
    // 检测用户密码是否正确
    String getPassword(Integer user_id) throws Exception;
}
