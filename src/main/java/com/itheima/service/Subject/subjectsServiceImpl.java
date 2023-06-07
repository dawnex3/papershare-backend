package com.itheima.service.Subject;


import com.itheima.mapper.Subject.subjectsMapper;
import com.itheima.model.Subject.subjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class subjectsServiceImpl implements subjectsService {
    @Autowired
    private subjectsMapper subjectMapper;

    @Override
    public Integer getMaxId() throws Exception {
        return subjectMapper.getMaxId();
    }

    @Override
    public List<subjects> getAllSubject() throws Exception {
        return subjectMapper.getAllSubject();
    }

    @Override
    public subjects getSubjectById(Integer subjectId) throws Exception {
        return subjectMapper.getSubjectById(subjectId);
    }

    @Override
    public List<subjects> getSubjectByName(String subjectName) throws Exception {
        return subjectMapper.getSubjectByName(subjectName);
    }

    @Override
    public void insertSubject(subjects subject) throws Exception {
        subjectMapper.insertSubject(subject);
    }

    @Override
    public void updateSubject(subjects subject) throws Exception {
        subjectMapper.updateSubject(subject);
    }

    @Override
    public void deleteSubject(subjects subject) throws Exception {
        subjectMapper.deleteSubject(subject);
    }
}

