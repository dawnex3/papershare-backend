package com.itheima.mapper.Subject;


import com.itheima.exception.NotFoundException;
import com.itheima.model.Subject.subjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Repository
public class subjectsMapperImp implements subjectsMapper {
    @Autowired
    private MongoTemplate mongoTemplate;
    //获取当前id最大值
    public Integer getMaxId() {
        Query query = new Query();
        //排序规则 把id倒序
        Sort sort = Sort.by(Sort.Direction.DESC,"_id");
        query.with(sort);
        List<subjects> comments = mongoTemplate.find(query, subjects.class);
        subjects subject1 = comments.get(0);
        Integer max_id = subject1.getId();
        return max_id;
    }

    @Override
    public List<subjects> getAllSubject() throws Exception {
        List<subjects> subject = mongoTemplate.findAll(subjects.class);
        if(subject.size() == 0){
            throw new NotFoundException("the subject is not exist");
        }
        return subject;
    }

    //根据id获取一个专业的信息。
    public subjects getSubjectById(Integer subjectId) throws Exception{
        subjects subject = mongoTemplate.findById(subjectId, subjects.class);
        if(subject == null){
            throw new NotFoundException("the subject is not exist");
        }
        return subject;
    }
    //根据name获取一个专业的信息。
    public List<subjects> getSubjectByName(String subjectName) throws Exception{
        Query query = new Query(Criteria.where("name").is(subjectName));
        List<subjects> subject = mongoTemplate.find(query, subjects.class);
        if(subject.size() == 0){
            throw new NotFoundException("the subject is not exist");
        }
        return subject;
    }
    //创建一个专业。
    public void insertSubject(subjects subject) throws Exception{
        try{
            mongoTemplate.save(subject);
        }catch (Exception e){
            throw new NotFoundException("failed to create the subject");
        }
    }
    //修改专业信息。
    public void updateSubject(subjects subject) throws Exception{
        try{
            mongoTemplate.save(subject);
        }catch (Exception e){
            throw new NotFoundException("failed to modify the subject");
        }
    }
    //删除一个专业。只是改变此专业的状态为“删除”，不会将其从数据库中删除。
    public void deleteSubject(subjects subject) throws Exception{
        try{
            mongoTemplate.save(subject);
        }catch (Exception e) {
            throw new NotFoundException("failed to delete the subject");
        }
    }
}