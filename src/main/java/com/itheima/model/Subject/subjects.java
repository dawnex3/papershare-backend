package com.itheima.model.Subject;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection="subjects")

@Data
public class subjects implements  Serializable{
    @Id
    //专业编号
    private Integer id;
    //专业名称
    private String name;
    //专业描述
    private String describe = "";
    //状态（正常0 或 已被删除1）
    private Integer status = 0;

    //static public Integer idCount = 102;  //要改，在每次需要新建id时mongotemplate获取当前最大id值然后加一


    public Integer getId() {
        return id;
    }

    public void setId(Integer count) { id = count; }

    public void setName(String name1){
        name = name1;
    }

    public void setDescribe(String d){
        describe = d;
    }

    public void setStatus(Integer count){
        status = count;
    }
}