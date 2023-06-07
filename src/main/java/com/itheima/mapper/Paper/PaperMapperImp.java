package com.itheima.mapper.Paper;

import com.itheima.exception.NotFoundException;
import com.itheima.model.Course.Courses;
import com.itheima.model.Paper.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//实现类
@Repository
public class PaperMapperImp implements PaperMapper{
    //适用MongoTemplat模板类实现数据库操作
    @Autowired
    private MongoTemplate mongoTemplate;
    // 添加一张试卷
    public void addOnePaper(Paper paper) throws Exception{
        try{
            mongoTemplate.save(paper);
        }catch (Exception e){
            throw new NotFoundException("添加试卷失败");
        }
    }

    @Override
    public void addAnswer(Integer paperId, Integer answerId) throws Exception {
        try{
            Paper paper = getOnePaperByPaperId(paperId);
            paper.addAnswers(answerId);
            mongoTemplate.save(paper);
        }catch (Exception e){
            throw new NotFoundException("添加答案失败");
        }
    }

    // 删除一张试卷
    public void deleteOnePaperById(Integer paperId) throws Exception{
        Paper paper = mongoTemplate.findById(paperId, Paper.class);
        if(paper == null){
            throw new NotFoundException("the paper is not exist");
        }
        try{
            mongoTemplate.remove(paper);
        }catch (Exception e){
            throw new NotFoundException("删除试卷失败");

        }
    }
    // 修改一张试卷的信息
    public void updateOnePaper(Paper paper) throws Exception{
        try {
            mongoTemplate.save(paper);
        }catch (Exception e){
            throw new NotFoundException("保存用户状态失败");
        }
    }
    // 根据主键id获取一张试卷
    public Paper getOnePaperByPaperId(Integer paperId) throws Exception{
        Paper paper = mongoTemplate.findById(paperId, Paper.class);
        if(paper == null){
            throw new NotFoundException("the paper is not exist");
        }
        return paper;
    }
    // 获取全部试卷
    public List<Paper> getAllPaper() throws Exception{
        List<Paper> papers= mongoTemplate.findAll(Paper.class);
        if(papers.size() == 0){
            throw new NotFoundException("no paper exist");
        }
        return papers;
    }
    //获取当前id最大值
    public Integer getMaxId() {
        Query query = new Query();
        //排序规则 把id倒序
        Sort sort = Sort.by(Sort.Direction.DESC,"_id");
        query.with(sort);
        List<Paper> papers = mongoTemplate.find(query, Paper.class);
        Paper paper = papers.get(0);
        Integer max_id = paper.getId();
        return max_id;
    }

    @Override
    public List<Paper> getPaperBy(Integer id, String name, Integer course_id, Integer year, Integer year_from, Integer status) throws Exception {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(id != null){
            criteria.and("_id").is(id);
        }
        if(name != null){
            criteria.and("name").regex(".*"+name+".*");
        }
        if(course_id != null){
            criteria.and("course_id").is(course_id);
        }
        if(year != null){
            criteria.and("year").is(year);
        }else if(year_from != null){
            criteria.and("year").gte(year_from);
        }
        if(status != null){
            criteria.and("status").is(status);
        }
        query.addCriteria(criteria);
        List<Paper> papers = mongoTemplate.find(query, Paper.class);
        if(papers.size() == 0){
            throw new NotFoundException("paper not exist");
        }
        return papers;
    }

}
