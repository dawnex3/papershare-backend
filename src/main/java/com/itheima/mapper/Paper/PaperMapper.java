package com.itheima.mapper.Paper;


import com.itheima.model.Paper.Paper;

import java.util.List;

public interface PaperMapper {
    // 添加一张试卷
    void addOnePaper(Paper paper) throws Exception;
    void addAnswer(Integer paperId, Integer answerId) throws Exception;
    // 删除一张试卷
    void deleteOnePaperById(Integer paperId) throws Exception;
    // 修改一张试卷的信息
    void updateOnePaper(Paper paper) throws Exception;
    // 根据主键id获取一张试卷
    Paper getOnePaperByPaperId(Integer paperId) throws Exception;
    // 获取全部试卷
    List<Paper> getAllPaper() throws Exception;
    //获取当前id最大值
    Integer getMaxId() throws Exception;

    List<Paper> getPaperBy(Integer id, String name, Integer course_id, Integer year, Integer year_from, Integer status) throws Exception;
}
