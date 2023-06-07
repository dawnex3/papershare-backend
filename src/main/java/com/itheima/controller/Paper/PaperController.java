package com.itheima.controller.Paper;


import com.itheima.controller.ResponseData;
import com.itheima.model.Course.Courses;
import com.itheima.model.Paper.Paper;
import com.itheima.model.User.User;
import com.itheima.service.Course.coursesService;
import com.itheima.service.Paper.PaperService;
import com.itheima.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/papers")
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private coursesService coursesService;
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseData<Paper> newPaper(@RequestBody Map<String,Object>map) throws Exception{
        Paper paper = new Paper();
        paper.setId(paperService.getMaxId()+1);
        paper.setName((String) map.get("name"));
        Integer course_id = (Integer) map.get("course_id");
        coursesService.getCoursesById(course_id);
        paper.setCourse_id(course_id);
        paper.setYear((Integer) map.get("year"));
        if(map.get("comments")!=null) {
            List<Object> list = (List<Object>) map.get("comments");
            List<Paper.Comment> comments = new ArrayList<>();
            int i=0;
            for (Object c : list) {
                Map<String, Object> map2 = (Map<String, Object>) c;
                Integer user_id = (Integer) map2.get("user_id");
                userService.getOneUserByUserId(user_id);
                String text = (String) map2.get("text");
                Paper.Comment comment = new Paper.Comment(user_id, text,i);
                comments.add(comment);
                i++;
            }
            paper.setComments(comments);
        }
        paperService.addOnePaper(paper);
        ResponseData<Paper> responseData= new ResponseData<>(paper, "新建成功", 200);
        return responseData;
    }

    @GetMapping
    public ResponseData<List<Paper>> getInfo(HttpServletRequest request) throws Exception{
        String id = request.getParameter("id");
        String course_id = request.getParameter("course_id");
        String name = request.getParameter("name");
        String year = request.getParameter("year");
        String year_from = request.getParameter("year_from");
        String status = request.getParameter("status");
        String favor_by = request.getParameter("favor_by");

        List<Paper> papers = paperService.getPaperBy(id,name,course_id,year,year_from,status);
        if(favor_by != null){
            Integer userId = Integer.valueOf(favor_by);
            User user1 = userService.getOneUserByUserId(userId);
            List<Integer> paperIdList = user1.getFavorites();
            List<Paper> favorPaper = papers.stream().filter(item -> paperIdList.contains(item.getId())).collect(Collectors.toList());

            return new ResponseData<>(favorPaper, "获得收藏信息", 200);
        } else {
            return new ResponseData<>(papers, "获得试卷信息", 200);
        }
    }


    @DeleteMapping("/{paper_id}")
    public ResponseData<Object>  DeletePaper(@PathVariable Integer paper_id) throws Exception{
        paperService.deleteOnePaperById(paper_id);
        ResponseData<Object> responseData= new ResponseData<>(null, "删除成功", 200);
        return responseData;
    }

    @PostMapping("/{paper_id}/answers")
    public ResponseData<Object> AddAnswer(@PathVariable Integer paper_id, @RequestBody Map<String,Object>map) throws Exception{
        Paper paper = paperService.getOnePaperByPaperId(paper_id);
        Integer file_id = (Integer) map.get("file_id");
        paper.addAnswers(file_id);
        paperService.updateOnePaper(paper);
        ResponseData<Object> responseData= new ResponseData<>(null, "添加成功", 200);
        return responseData;
    }

    @DeleteMapping("/{paper_id}/answers/{file_id}")
    public ResponseData<Object>  DeleteAnswer(@PathVariable Integer paper_id, @PathVariable Integer file_id) throws Exception{
        Paper paper = paperService.getOnePaperByPaperId(paper_id);
        paper.deleteAnswers(file_id);
        paperService.updateOnePaper(paper);
        ResponseData<Object> responseData= new ResponseData<>(null, "删除成功", 200);
        return responseData;
    }

    @PostMapping("/{paper_id}/comments")
    public ResponseData<Object> AddComment(@PathVariable Integer paper_id, @RequestBody Map<String,Object>map) throws Exception{
        Paper paper = paperService.getOnePaperByPaperId(paper_id);
        String text = (String) map.get("comment");
        Integer user_id = (Integer) map.get("user_id");
        userService.getOneUserByUserId(user_id);
        Paper.Comment comment = new Paper.Comment(user_id, text,paper.getComments().size());
        paper.addComments(comment);
        paperService.updateOnePaper(paper);
        ResponseData<Object> responseData= new ResponseData<>(null, "添加成功", 200);
        return responseData;
    }

    @DeleteMapping("/{paper_id}/comments/{comment_no}")
    public ResponseData<Object>  DeleteComment(@PathVariable Integer paper_id, @PathVariable Integer comment_no) throws Exception{
        Paper paper = paperService.getOnePaperByPaperId(paper_id);
        paper.deleteComments(comment_no);
        paperService.updateOnePaper(paper);
        ResponseData<Object> responseData= new ResponseData<>(null, "删除成功", 200);
        return responseData;
    }

    @PutMapping("/{paper_id}")
   public ResponseData<Paper> UpdatePaper(@PathVariable Integer paper_id, @RequestBody Map<String,Object>map) throws Exception{
        Paper paper = paperService.getOnePaperByPaperId(paper_id);
        if (map.containsKey("name"))
            paper.setName((String) map.get("name"));
        if (map.containsKey("course_id")){
            Integer course_id = (Integer) map.get("course_id");
            coursesService.getCoursesById(course_id);
            paper.setCourse_id(course_id);
        }
        if (map.containsKey("status"))
            paper.setStatus((Integer) map.get("status"));
        if (map.containsKey("year"))
            paper.setYear((Integer) map.get("year"));
        if (map.containsKey("paper_id"))
            paper.setPaper_id((Integer) map.get("paper_id"));
        if (map.containsKey("comments")){
            if(map.get("comments")!=null) {
                List<Object> list = (List<Object>) map.get("comments");
                List<Paper.Comment> comments = new ArrayList<>();
                int i=0;
                for (Object c : list) {
                    Map<String, Object> map2 = (Map<String, Object>) c;
                    Integer user_id = (Integer) map2.get("user_id");
                    userService.getOneUserByUserId(user_id);
                    String text = (String) map2.get("text");
                    Paper.Comment comment = new Paper.Comment(user_id, text,i);
                    comments.add(comment);
                    i++;
                }
                paper.setComments(comments);
            }
        }
        paperService.updateOnePaper(paper);
        ResponseData<Paper> responseData= new ResponseData<>(paper, "修改成功", 200);
        return responseData;
    }


}
