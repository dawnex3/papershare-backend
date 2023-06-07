package com.itheima.service.Paper;

import com.itheima.mapper.Paper.PaperMapper;
import com.itheima.model.Paper.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImp implements PaperService{
    @Autowired
    private PaperMapper paperMapper;

    @Override
    public void addOnePaper(Paper paper) throws Exception {
        paperMapper.addOnePaper(paper);
    }

    @Override
    public void deleteOnePaperById(Integer paperId) throws Exception {
        paperMapper.deleteOnePaperById(paperId);
    }

    @Override
    public void updateOnePaper(Paper paper) throws Exception {
        paperMapper.updateOnePaper(paper);
    }

    @Override
    public Paper getOnePaperByPaperId(Integer paperId) throws Exception {
        return paperMapper.getOnePaperByPaperId(paperId);
    }

    @Override
    public List<Paper> getAllPaper() throws Exception {
        return paperMapper.getAllPaper();
    }

    @Override
    public Integer getMaxId() throws Exception{
        return paperMapper.getMaxId();
    }

    @Override
    public List<Paper> getPaperBy(String id, String name, String course_id, String year, String year_from, String status) throws Exception {
        return paperMapper.getPaperBy(
                id==null?null:Integer.valueOf(id),
                name,
                course_id==null?null:Integer.valueOf(course_id),
                year==null?null:Integer.valueOf(year),
                year_from==null?null:Integer.valueOf(year_from),
                status==null?null:Integer.valueOf(status));
    }

    @Override
    public void addAnswer(Integer paper_id, int i) throws Exception {
        paperMapper.addAnswer(paper_id,i);
    }
}
