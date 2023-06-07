package com.itheima.service.Subject;


import com.itheima.model.Subject.subjects;

import java.util.List;

public interface subjectsService {
    Integer getMaxId() throws Exception;
    List<subjects> getAllSubject() throws Exception;
    subjects getSubjectById(Integer subjectId) throws Exception;
    List<subjects> getSubjectByName(String subjectName) throws Exception;
    void insertSubject(subjects subject) throws Exception;
    void updateSubject(subjects subject) throws Exception;
    void deleteSubject(subjects subject) throws Exception;
}
