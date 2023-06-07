package com.itheima.controller.Course;


import com.itheima.controller.ResponseData;
import com.itheima.model.Course.Courses;
import com.itheima.model.Subject.subjects;
import com.itheima.model.User.User;
import com.itheima.service.Course.coursesService;
import com.itheima.service.Subject.subjectsService;
import com.itheima.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {
    @Autowired
    private coursesService courseService;
    @Autowired
    private subjectsService subjectService;
    @Autowired
    private UserService userService;

    @GetMapping
    //GET 课程信息
    public ResponseData<List<Courses>> getInfo(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String subject_id = request.getParameter("subject_id");
        String status = request.getParameter("status");
        String user_id = request.getParameter("subscribe_by");


        List<Courses> course = courseService.getCoursesBy(id,name,subject_id,status);
        if(user_id != null){
            Integer userId = Integer.valueOf(user_id);
            User user1 = userService.getOneUserByUserId(userId);
            List<Integer> courseIdList = user1.getSubscriptions();
            List<Courses> subscriptCourses = course.stream().filter(item -> courseIdList.contains(item.getId())).collect(Collectors.toList());

            return new ResponseData<>(subscriptCourses, "获得课程信息", 200);
        } else {
            return new ResponseData<>(course, "获得课程信息", 200);
        }
        /*
        if(id != null){
            Integer Id = Integer.valueOf(id);
            List<Courses> course = courseService.getCoursesById(Id);
            ResponseData<List<Courses>> responseData= new ResponseData<>(course, "获得课程"+id+"信息", 200);
            return responseData;
        }
        else{
            if(name != null){
                List<Courses> course = courseService.getCoursesByName(name);
                ResponseData<List<Courses>> responseData= new ResponseData<>(course, "获得课程"+name+"信息", 200);
                return responseData;
            }
            else if(subject_id != null){
                subjects subject = subjectService.getSubjectById(Integer.valueOf(subject_id));
                Integer subject_Id = Integer.valueOf(subject_id);
                List<Courses> course = courseService.getCoursesBySubject(subject_Id);
                ResponseData<List<Courses>> responseData= new ResponseData<>(course, "获得属于"+subject_id+"的课程信息", 200);
                return responseData;
            }
            else if(status != null){
                Integer Status = Integer.valueOf(status);
                List<Courses> course = courseService.getCoursesByStatus(Status);
                ResponseData<List<Courses>> responseData= new ResponseData<>(course, "获得课程信息", 200);
                return responseData;
            }
            else{//subscribe_by
                Integer userId = Integer.valueOf(user_id);
                User user1 = userService.getOneUserByUserId(userId);
                List<Integer> courseIdList = user1.getSubscriptions();

                List<Courses> subscriptCourses = new ArrayList<>();
                for(int i = 0; i < courseIdList.size(); i++){
                    Integer tmpCourseId = courseIdList.get(i);
                    List<Courses> tmpCoursesList = courseService.getCoursesById(tmpCourseId);
                    if(tmpCoursesList.size()==0){
                        continue;
                    }
                    Courses tmpCourse = tmpCoursesList.get(0);
                    subscriptCourses.add(tmpCourse);
                }

                ResponseData<List<Courses>> responseData= new ResponseData<>(subscriptCourses, "获得课程信息", 200);
                return responseData;
            }
        }*/


    }

    @PostMapping
    //POST 新建课程
    public ResponseData<Object> CreateCourse(@RequestBody Map<String,Object>map) throws Exception {
        Courses course = new Courses();

        Integer idCount = courseService.getMaxId();
        course.setId(idCount+1);

        course.setName((String) map.get("name"));
        course.setDescribe((String) map.get("describe"));
        course.setSubjectId((Integer) map.get("subject_id"));
        if(map.get("teachers") != null){
            course.setTeachers((String) map.get("teachers"));
        }
        else{
            course.setTeachers("");
        }
        course.setStatus(0);

        courseService.createCourse(course);

        Courses emptyCourse = new Courses();
        ResponseData<Object> responseData= new ResponseData<>(null,"添加成功", 200);
        return responseData;
    }

    @PutMapping("/{course_id}")
    //PUT 修改课程
    public ResponseData<Object> ModifyCourse(@PathVariable Integer course_id, @RequestBody Map<String,Object>map) throws Exception {
        List<Courses> Course = courseService.getCoursesById(course_id);
        Courses course = Course.get(0);

        String name = (String) map.get("name");
        String describe = (String) map.get("describe");
        String subject_id = String.valueOf(map.get("subject_id"));

        String status = String.valueOf(map.get("status"));

        String teachers = (String) map.get("teachers");

        if(name != null){
            course.setName(name);
        }
        if(describe != null){
            course.setDescribe(describe);
        }
        if(subject_id != null && !subject_id.equals("null")){
            Integer subject_Id = Integer.valueOf(subject_id);
            course.setSubjectId(subject_Id);
        }
        if(status != null && !status.equals("null")){
            Integer Status = Integer.valueOf(status);
            course.setStatus(Status);
        }
        if(teachers != null){
            course.setTeachers(teachers);
        }

        courseService.updateCourse(course);
        Courses emptyCourse = new Courses();
        ResponseData<Object> responseData= new ResponseData<>(null,"修改成功", 200);
        //ResponseData<Courses> responseData= new ResponseData<>("修改成功", 200);
        return responseData;
    }

    @DeleteMapping("/{course_id}")
    //DEL 删除课程
    public ResponseData<Object> DeleteCourse(@PathVariable Integer course_id) throws Exception {
        List<Courses> Course = courseService.getCoursesById(course_id);
        Courses course = Course.get(0);

        course.setStatus(1);

        courseService.updateCourse(course);
        Courses emptyCourse = new Courses();
        ResponseData<Object> responseData= new ResponseData<>( null,"删除成功", 200);
        return responseData;
    }
}
