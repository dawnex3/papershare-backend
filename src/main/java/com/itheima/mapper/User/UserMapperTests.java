package com.itheima.mapper.User;

import com.itheima.model.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void addOneStudent() throws Exception{
        for(Integer count=0; count<2;count++){
           User user = new User();
           user.setId(count);
           user.setName("xiao"+count);
           user.setPassword("test"+count);
           userMapper.addOneUser(user);
        }
    }
    @Test
    void deleteOneUserById() throws Exception{
        userMapper.deleteOneUserById(2);
    }
    @Test
    void updateOneUser() throws Exception{
        User user = userMapper.getOneUserByUserId(3);
        user.setPassword("1234");
        userMapper.updateOneUser(user);
    }
    @Test
    void getOneUserByUserId() throws Exception{
        System.out.println(userMapper.getOneUserByUserId(3));
    }
    @Test
    void getAllUser() throws Exception{
        List<User> userList = userMapper.getAllUser();
        userList.forEach(System.out::println);
    }

}
