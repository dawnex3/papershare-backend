package com.itheima.model.User;

import com.itheima.exception.NotFoundException;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Document(collection="users")
public class User implements Serializable {
    //用户id   必须指定id列
    @Id
    private Integer id;
    //用户名
    private String name;
    //用户密码
    private String password = "";
    // 类型
    private Integer type = 2;
    // 是否被禁
    private Boolean isbanned = false;
    // 头像
    private String photo = "";
    //订阅课程的id
    private List<Integer> subscriptions = new ArrayList<>();
    //收藏的试卷id
    private List<Integer> favorites = new ArrayList<>();

    public void setId(Integer count) { id = count; }

    public void setName(String name1){
        name = name1;
    }

    public void setPassword(String password1){
        password = password1;
    }

    public void setType(Integer type1){// 用户类型
        type = type1;
    }

    public void setBanned(Boolean isbanned1){// 是否封禁
        isbanned = isbanned1;
    }

    public void setPhoto(String photo1){//头像
        photo = photo1;
    }

    public void addSubscriptions(Integer course_id){subscriptions.add(course_id);}

    public void deleteSubscriptions(Integer course_id) throws Exception{
        if(!subscriptions.contains(course_id)){
            throw new NotFoundException("user doesn't subscript "+ course_id.toString());
        }
        //正确
        subscriptions.removeIf(i -> i.equals(course_id));
    }

    public void addFavorites(Integer id){
        favorites.add(id);
    }

    public void deleteFavorites(Integer id) throws Exception{
        if(!favorites.contains(id)){
            throw new NotFoundException("user doesn't collect "+ id.toString());
        }
        //正确
        favorites.removeIf(i -> i.equals(id));
    }

    public String getPassword(){
        return password;
    }

    public List<Integer> getSubscriptions(){
        return subscriptions;
    }
    public Integer getId(){
        return id;
    }
}
