package com.itheima.model.Paper;

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
@Document(collection="papers")
public class Paper implements Serializable{
    //试卷id
    @Id
    private Integer id;
    //试卷名称
    private String name;
    //所属科目
    private Integer course_id;
    //正常，删除，等待审核
    private Integer status;
    //试题年份
    private Integer year;
    //试卷文件的id
    private Integer paper_id;
    //答案文件的id
    private List<Integer> answers = new ArrayList<>();
    //评论{“id”：，“text”:,}
    private List<Comment> comments = new ArrayList<>();

    public static class Comment {
        @Id
        private Integer id;
        private Integer user_id;
        private String text;
        public Comment(Integer user_id, String text, Integer id){
            this.user_id = user_id;
            this.text = text;
            this.id = id;
        }

        public Integer getUser_id() {
            return user_id;
        }

        public String getText() {
            return text;
        }
    }

    public void setId(Integer count) {id = count; }

    public void setComments(List<Comment> c ){comments = c;}

    public void setName(String name1) {name = name1; }

    public void setCourse_id(Integer c_id) {course_id = c_id; }

    public void setStatus(Integer sta) {status = sta; }

    public void  setYear(Integer y) {year = y; }

    public void setPaper_id(Integer p_id) {paper_id = p_id; }

    public void addAnswers(Integer file_id) {answers.add(file_id); }

    public void deleteAnswers(Integer file_id) throws Exception{
        if(!answers.contains(file_id)){
            throw new NotFoundException("no anwser "+ file_id.toString());
        }
        answers.removeIf(i -> i.equals(file_id));
    }

    public void addComments(Comment comment) {
            comments.add(comment);
    }

    public void deleteComments(Integer c_no) throws Exception{
        if(comments.size()<(c_no+1)){
            throw new NotFoundException("no comments "+ c_no.toString());
        }
        comments.remove((int)c_no);
    }
    public Integer getId(){
        return id;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
