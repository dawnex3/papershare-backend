package com.itheima.service.Paper;


import com.itheima.model.Paper.Paper;

import java.util.List;

public interface PaperService {
    void addOnePaper(Paper paper) throws Exception;
    void deleteOnePaperById(Integer paperId) throws Exception;
    void updateOnePaper(Paper paper) throws Exception;
    Paper  getOnePaperByPaperId(Integer paperId) throws Exception;
    List<Paper> getAllPaper() throws Exception;
    Integer getMaxId() throws Exception;

    List<Paper> getPaperBy(String id, String name, String course_id, String year, String year_from, String status) throws Exception;

    void addAnswer(Integer paper_id, int i) throws Exception;
}
