package com.itheima.mapper.File;

import com.itheima.exception.NotFoundException;
import com.itheima.mapper.File.FileMapper;
import com.itheima.model.Course.Courses;
import com.itheima.model.File.File;
import com.itheima.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileMapperImp implements FileMapper{
    //使用MongoTemplat模板类实现数据库操作
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer getMaxId() throws Exception {
        Query query = new Query();
        //排序规则 把id倒序
        Sort sort = Sort.by(Sort.Direction.DESC,"_id");
        query.with(sort);
        List<File> files = mongoTemplate.find(query, File.class);
        return files.get(0).getId();
    }

    @Override
    public void addFile(File file) throws Exception {
        try{
            mongoTemplate.save(file);
        }catch (Exception e){
            throw new NotFoundException("添加文件失败");
        }
    }

    @Override
    public File getOneFile(Integer fileId) throws Exception {
        try{
            File file = mongoTemplate.findById(fileId, File.class);
            return file;
        }catch (Exception e){
            throw new NotFoundException("file not exist");
        }
    }

    @Override
    public List<File> getFileBy(Integer id, Integer uploader, Integer subject_id, Integer type) throws Exception {
        Query query = new Query();
        //不要返回文件内容
        query.fields().exclude("data");
        Criteria criteria = new Criteria();
        if(id != null){
            criteria.and("_id").is(id);
        }
        if(uploader != null){
            criteria.and("uploader").is(uploader);
        }
        if(subject_id != null){
            criteria.and("subject_id").is(subject_id);
        }
        if(type != null){
            criteria.and("type").is(type);
        }
        query.addCriteria(criteria);
        List<File> files = mongoTemplate.find(query, File.class);
        if(files.size() == 0){
            throw new NotFoundException("file not exist");
        }
        return files;
    }

    @Override
    public void deleteById(Integer file_id) throws NotFoundException {
        File file = mongoTemplate.findById(file_id, File.class);
        if(file == null){
            throw new NotFoundException("file not exist");
        }
        try{
            mongoTemplate.remove(file);
        }catch (Exception e){
            throw new NotFoundException("删除文件失败");
        }
    }
}
