package com.itheima.controller.Auth;

import com.itheima.controller.ResponseData;
import com.itheima.exception.NotFoundException;
import com.itheima.model.User.User;
import com.itheima.service.User.UserService;
import com.itheima.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseData<Map<String, Object>> login(@RequestBody Map<String,Object> map) throws Exception {
        System.out.println("收到登录信息");
        Map<String, Object> map1 = new HashMap<>();
        Integer id = (Integer) map.get("student_id");
        String password = (String) map.get("password");
        // 检验账号和密码是否正确
        if (userService.getPassword(id).equals(password)) {
            String token = TokenUtil.sign(id, password);
            if (token != null) {
                map1.put("token", token);
                map1.put("id", id);
                // return ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                ResponseData<Map<String, Object>> responseData = new ResponseData<>(map1, "登录成功", 200);
                return responseData;
            } else {
                throw new NotFoundException("登录失败");
            }
        } else {
            throw new NotFoundException("账号或密码错误");
        }
    }
    @PostMapping("/register")
    public ResponseData<User> register(@RequestBody Map<String,Object>map) throws Exception {
        User user = new User();
        user.setId((Integer) map.get("student_id"));
        user.setName((String) map.get("name"));
        user.setPassword((String) map.get("password"));
        user.setType(2);
        userService.addOneUser(user);
        ResponseData<User> responseData = new ResponseData<>(user, "修改用户状态成功", 200);
        return responseData;
    }
}