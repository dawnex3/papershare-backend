package com.itheima.model.Course;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection="courses")

@Data
public class Courses implements  Serializable{
    @Id
    //课程编号
    private Integer id;
    //课程名称
    private String name;
    //所属专业
    private Integer subject_id;
    //状态（正常0 或 已被删除1）
    private Integer status = 0;
    //专业描述
    private String describe = "";
    //教师
    private String teachers = "";

    //static public Integer idCount = 200;

    public Integer getId(){return id;}

    public void setId(Integer count) { id = count; }

    public void setName(String name1){
        name = name1;
    }

    public void setSubjectId(Integer count){
        subject_id = count;
    }

    public void setDescribe(String d){
        describe = d;
    }

    public void setStatus(Integer count){
        status = count;
    }

    public void setTeachers(String teachers) { this.teachers = teachers; }

    public String getTeachers() { return this.teachers; }
}