package com.itheima.controller.User;

import com.itheima.controller.ResponseData;
import com.itheima.model.User.User;
import com.itheima.service.Course.coursesService;
import com.itheima.service.Paper.PaperService;
import com.itheima.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private coursesService coursesService;
    @Autowired
    private PaperService paperService;
    @GetMapping
    public ResponseData<List<User>> getInfo(HttpServletRequest request) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        List<User> users = new ArrayList<>();
        User user = userService.getOneUserByUserId(id);
        users.add(user);
        ResponseData<List<User>> responseData= new ResponseData<>(users, "获得用户"+id+"信息", 200);
        return responseData;
    }

    @PostMapping("/{user_id}/subscriptions")
    public ResponseData<Object>  SubscriptCourse(@PathVariable Integer user_id, @RequestBody Map<String,Object>map) throws Exception {
        User user =  userService.getOneUserByUserId(user_id);
        Integer course_id = (Integer) map.get("course_id");
        coursesService.getCoursesById(course_id);
        user.addSubscriptions(course_id);
        userService.updateOneUser(user);
        ResponseData<Object> responseData= new ResponseData<>(null, "收藏成功", 200);
        return responseData;
    }

    @PostMapping("/{user_id}/favorites")
    public ResponseData<Object> CollectPaper(@PathVariable Integer user_id, @RequestBody Map<String,Object>map) throws Exception {
        User user = userService.getOneUserByUserId(user_id);
        Integer paper_id = (Integer) map.get("paper_id");
        paperService.getOnePaperByPaperId(paper_id);
        user.addFavorites(paper_id);
        userService.updateOneUser(user);
        ResponseData<Object> responseData = new ResponseData<>(null, "添加试题成功", 200);
        return responseData;
    }

    @DeleteMapping("/{user_id}/subscriptions/{course_id}")
    public ResponseData<Object>   DeleteCourse(@PathVariable Integer user_id, @PathVariable Integer course_id) throws Exception{
        User user = userService.getOneUserByUserId(user_id);
        user.deleteSubscriptions(course_id);
        userService.updateOneUser(user);
        ResponseData<Object> responseData = new ResponseData<>(null, "删除课程成功", 200);
        return responseData;
    }

    @DeleteMapping("/{user_id}/favorites/{paper_id}")
    public ResponseData<Object>  DeletePaper(@PathVariable Integer user_id, @PathVariable Integer paper_id) throws Exception{
        User user = userService.getOneUserByUserId(user_id);
        user.deleteFavorites(paper_id);
        userService.updateOneUser(user);
        ResponseData<Object> responseData = new ResponseData<>(null, "删除试卷成功", 200);
        return responseData;
    }

    @DeleteMapping("/{user_id}")
    public ResponseData<Object>  DeleteUser(@PathVariable Integer user_id) throws Exception{
        userService.deleteOneUserById(user_id);
        ResponseData<Object> responseData = new ResponseData<>(null, "删除用户成功", 200);
        return responseData;
    }

    @PutMapping("/{user_id}")
    public ResponseData<Object> UpdateUser(@PathVariable Integer user_id, @RequestBody Map<String,Object>map) throws Exception {
        User user = userService.getOneUserByUserId(user_id);
        if (map.containsKey("name"))
            user.setName((String) map.get("name"));
        if (map.containsKey("password"))
            user.setPassword((String) map.get("password"));
        if (map.containsKey("isbanned"))
            user.setBanned((boolean) map.get("isbanned"));
        if (map.containsKey("photo"))
            user.setPhoto((String) map.get("photo"));
        userService.updateOneUser(user);
        ResponseData<Object> responseData = new ResponseData<>(null, "修改用户状态成功", 200);
        return responseData;
    }
}
