package com.itheima.mapper.Course;


import com.itheima.model.Course.Courses;

import java.util.List;

public interface coursesMapper {
    //获取当前id最大值
    Integer getMaxId() throws Exception;
    //多条件查询
    List<Courses> getCoursesBy(Integer id, String name, Integer subjectId, Integer statue) throws Exception;
    //获取所有课程的信息。
    List<Courses> getAllCourses() throws Exception;
    //根据id获取课程的信息。
    List<Courses> getCoursesById(Integer courseId) throws Exception;
    //根据name获取课程的信息。
    List<Courses> getCoursesByName(String courseName) throws Exception;
    //根据所属专业id获取课程的信息。
    List<Courses> getCoursesBySubject(Integer subjectId) throws Exception;
    // 根据课程状态获取课程的信息。
    List<Courses> getCoursesByStatus(Integer s) throws Exception;
    //创建一个课程。
    void createCourse(Courses course) throws Exception;
    //修改课程信息。
    void updateCourse(Courses course) throws Exception;
    //删除一个课程。只是改变此课程的状态为“删除”，不会将其从数据库中删除。
    void deleteCourse(Courses course) throws Exception;
}