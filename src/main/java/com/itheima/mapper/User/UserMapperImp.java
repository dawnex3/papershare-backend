package com.itheima.mapper.User;

import com.itheima.exception.NotFoundException;
import com.itheima.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// 实现类
@Repository
public class UserMapperImp implements UserMapper {
    //适用MongoTemplat模板类实现数据库操作
    @Autowired
    private MongoTemplate mongoTemplate;
    // 添加一个用户
    public void addOneUser(User user) throws NotFoundException {
        try{
            mongoTemplate.save(user);
        }catch (Exception e){
            throw new NotFoundException("添加用户失败");
        }
    }
    // 删除一个用户
    public void deleteOneUserById(Integer userId) throws Exception{
        User user = mongoTemplate.findById(userId, User.class);
        if(user == null){
            throw new NotFoundException("the user is not exist");
        }
        try{
            mongoTemplate.remove(user);
        }catch (Exception e){
            throw new NotFoundException("删除用户失败");
        }

    }
    // 修改一个用户的信息
    public void updateOneUser(User user) throws Exception {
        if(user.getPassword().equals("")){
            user.setPassword(getPassword(user.getId()));
        }
        try {
            mongoTemplate.save(user);
        }catch (Exception e){
            throw new NotFoundException("保存用户状态失败");
        }

    }
    // 根据主键id获取一位用户
    public User getOneUserByUserId(Integer userId) throws Exception{
        try{
            User user = mongoTemplate.findById(userId, User.class);
            user.setPassword("");
            return user;
        }catch (Exception e){
            throw new NotFoundException("the user is not exist");
        }
    }
    // 获取全部用户
    public List<User> getAllUser() throws Exception{
        try{
            List<User> users = mongoTemplate.findAll(User.class);
            for(User user : users){
                user.setPassword("");
            }
            return users;
        }catch (Exception e){
            throw new NotFoundException("no user exist");
        }
    }
    // 检测用户密码是否正确
    public String getPassword(Integer user_id) throws Exception{
        try{
            User user = mongoTemplate.findById(user_id, User.class);
            return user.getPassword();
        }catch (Exception e){
            throw new NotFoundException("the user is not exist");
        }
    }
}
