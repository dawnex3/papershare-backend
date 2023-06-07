package com.itheima.mapper.Subject;


import com.itheima.model.Subject.subjects;

import java.util.List;

public interface subjectsMapper {
    //获取当前id最大值
    Integer getMaxId() throws Exception;
    //根据所有专业的信息。
    List<subjects> getAllSubject() throws Exception;
    //根据id获取一个专业的信息。
    subjects getSubjectById(Integer subjectId) throws Exception;
    //根据name获取专业的信息。
    List<subjects> getSubjectByName(String subjectName) throws Exception;
   //创建一个专业。
    void insertSubject(subjects subject) throws Exception;
    //修改专业信息。
    void updateSubject(subjects subject) throws Exception;
    //删除一个专业。只是改变此专业的状态为“删除”，不会将其从数据库中删除。
    void deleteSubject(subjects subject) throws Exception;

}
