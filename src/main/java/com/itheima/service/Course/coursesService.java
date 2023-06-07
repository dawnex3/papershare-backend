package com.itheima.service.Course;


import com.itheima.model.Course.Courses;

import java.util.List;

public interface coursesService {
    Integer getMaxId() throws Exception;
    List<Courses> getCoursesBy(String id, String name, String subjectId, String statue) throws Exception;
    List<Courses> getAllCourses() throws Exception;
    List<Courses> getCoursesById(Integer courseId) throws Exception;
    List<Courses> getCoursesByName(String courseName) throws Exception;
    List<Courses> getCoursesBySubject(Integer subjectId) throws Exception;
    List<Courses> getCoursesByStatus(Integer s) throws Exception;
    void createCourse(Courses course) throws Exception;
    void updateCourse(Courses course) throws Exception;
    void deleteCourse(Courses course) throws Exception;
}