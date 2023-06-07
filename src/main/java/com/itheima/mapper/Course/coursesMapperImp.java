package com.itheima.mapper.Course;


import com.itheima.exception.NotFoundException;
import com.itheima.model.Course.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

@Repository
public class coursesMapperImp implements coursesMapper {
    @Autowired
    private MongoTemplate mongoTemplate;

    //获取当前id最大值
    public Integer getMaxId() {
        Query query = new Query();
        //排序规则 把id倒序
        Sort sort = Sort.by(Sort.Direction.DESC,"_id");
        query.with(sort);
        List<Courses> comments = mongoTemplate.find(query, Courses.class);
        Courses course1 = comments.get(0);
        Integer max_id = course1.getId();
        return max_id;
    }
    //多条件查询
    public List<Courses> getCoursesBy(Integer id, String name, Integer subjectId, Integer statue) throws Exception{
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(id != null){
            criteria.and("_id").is(id);
        }
        if(name != null){
            criteria.and("name").regex(".*"+name+".*");
        }
        if(subjectId != null){
            criteria.and("subject_id").is(subjectId);
        }
        if(statue != null){
            criteria.and("statue").is(statue);
        }
        query.addCriteria(criteria);
        List<Courses> courses = mongoTemplate.find(query, Courses.class);
        if(courses.size() == 0){
            throw new NotFoundException("the course is not exist");
        }
        return courses;
    }

    @Override
    public List<Courses> getAllCourses() throws Exception {
        List<Courses> courses = mongoTemplate.findAll(Courses.class);
        if(courses.size() == 0){
            throw new NotFoundException("the course is not exist");
        }
        return courses;
    }

    //根据id获取课程的信息。
    public List<Courses> getCoursesById(Integer courseId) throws Exception {
        List<Courses> rList = new ArrayList<>();
        Courses course = mongoTemplate.findById(courseId, Courses.class);
        if(course == null){
            throw new NotFoundException("the course is not exist");
        }
        rList.add(course);
        return rList;
    }

    //根据name获取课程的信息。
    public List<Courses> getCoursesByName(String courseName) throws Exception {
        Query query = new Query(Criteria.where("name").is(courseName));
        List<Courses> courses = mongoTemplate.find(query, Courses.class);
        if(courses.size() == 0){
            throw new NotFoundException("the course is not exist");
        }
        return courses;
    }

    //根据所属专业id获取课程的信息。
    public List<Courses> getCoursesBySubject(Integer subjectId) throws Exception {


        Query query = new Query(Criteria.where("subject_id").is(subjectId));
        List<Courses> courses = mongoTemplate.find(query, Courses.class);

        return courses;
    }

    // 根据课程状态获取课程的信息。
    public List<Courses> getCoursesByStatus(Integer s) throws Exception {
        Query query = new Query(Criteria.where("status").is(s));
        List<Courses> courses = mongoTemplate.find(query, Courses.class);
        if(courses.size() == 0){
            throw new NotFoundException("the course is not exist");
        }
        return courses;
    }

    //创建一个课程。
    public void createCourse(Courses course) throws Exception {
        try{
            mongoTemplate.save(course);
        }catch (Exception e){
            throw new NotFoundException("failed to create the course");
        }
    }
    //修改课程信息。
    public void updateCourse(Courses course) throws Exception {
        try{
            mongoTemplate.save(course);
        }catch (Exception e){
            throw new NotFoundException("failed to modify the course");
        }
    }
    //删除一个课程。只是改变此课程的状态为“删除”，不会将其从数据库中删除。
    public void deleteCourse(Courses course) throws Exception {
        try{
            mongoTemplate.save(course);
        }catch (Exception e){
            throw new NotFoundException("failed to delete the course");
        }
    }

}