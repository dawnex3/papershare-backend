package com.itheima.service.Course;


import com.itheima.mapper.Course.coursesMapper;
import com.itheima.model.Course.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class coursesServiceImpl implements coursesService {
    @Autowired
    private coursesMapper courseMapper;

    @Override
    public Integer getMaxId() throws Exception {
        return courseMapper.getMaxId();
    }

    @Override
    public List<Courses> getCoursesBy(String id, String name, String subjectId, String statue) throws Exception {

        return courseMapper.getCoursesBy(id == null ? null : Integer.valueOf(id), name,
                subjectId == null ? null : Integer.valueOf(subjectId), statue == null ? null : Integer.valueOf(statue));
    }

    @Override
    public List<Courses> getAllCourses() throws Exception {
        return courseMapper.getAllCourses();
    }

    @Override
    public List<Courses> getCoursesById(Integer courseId) throws Exception {
        return courseMapper.getCoursesById(courseId);
    }

    @Override
    public List<Courses> getCoursesByName(String courseName) throws Exception {
        return courseMapper.getCoursesByName(courseName);
    }

    @Override
    public List<Courses> getCoursesBySubject(Integer subjectId) throws Exception {
        return courseMapper.getCoursesBySubject(subjectId);
    }

    @Override
    public List<Courses> getCoursesByStatus(Integer s) throws Exception {
        return courseMapper.getCoursesByStatus(s);
    }

    @Override
    public void createCourse(Courses course) throws Exception {
        courseMapper.createCourse(course);
    }

    @Override
    public void updateCourse(Courses course) throws Exception {
        courseMapper.updateCourse(course);
    }

    @Override
    public void deleteCourse(Courses course) throws Exception {
        courseMapper.deleteCourse(course);
    }
}
