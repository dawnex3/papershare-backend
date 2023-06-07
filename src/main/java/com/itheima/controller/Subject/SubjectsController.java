package com.itheima.controller.Subject;

import com.itheima.controller.ResponseData;
import com.itheima.model.Subject.subjects;
import com.itheima.service.Subject.subjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/subjects")
public class SubjectsController {
    @Autowired
    private subjectsService subjectsService;

    @GetMapping
    //GET 专业信息
    public ResponseData<List<subjects>> getInfo(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        if(id != null && !id.equals("")){
            Integer Id = Integer.valueOf(id);
            subjects subject = subjectsService.getSubjectById(Id);
            List<subjects> Subjects = new ArrayList<>();
            Subjects.add(subject);
            return new ResponseData<>(Subjects, "获得专业"+id+"信息", 200);
        }
        else if(name != null && !name.equals("")){
            List<subjects> subject = subjectsService.getSubjectByName(name);
            return new ResponseData<>(subject, "获得专业"+name+"信息", 200);
        } else {
            List<subjects> subject = subjectsService.getAllSubject();
            return new ResponseData<>(subject, "获得所有专业信息", 200);
        }
    }

    @PostMapping
    //POST 创建专业
    public ResponseData<subjects> CreateSubject(@RequestBody Map<String,Object>map) throws Exception {
        subjects subject = new subjects();

        Integer idCount = subjectsService.getMaxId();
        //Integer idCount = 105;
        subject.setId(idCount+1);

        subject.setName((String) map.get("name"));
        subject.setDescribe((String) map.get("describe"));
        subject.setStatus(0);

        subjectsService.insertSubject(subject);

        ResponseData<subjects> responseData= new ResponseData<>(subject, "添加成功", 200);
        return responseData;
    }

    @PutMapping("/{subject_id}")
    //PUT 修改专业
    public ResponseData<subjects> ModifySubject(@PathVariable Integer subject_id, @RequestBody Map<String,Object>map) throws Exception {
        subjects subject = subjectsService.getSubjectById(subject_id);
        String name = (String) map.get("name");
        String describe = (String) map.get("describe");

        if(name != null){
            subject.setName(name);
        }
        if(describe != null){
            subject.setDescribe(describe);
        }

        subjectsService.updateSubject(subject);
        ResponseData<subjects> responseData= new ResponseData<>(subject, "修改成功", 200);
        return responseData;
    }

    @DeleteMapping("/{subject_id}")
    //DEL 删除专业
    public ResponseData<Object> DeleteSubject(@PathVariable Integer subject_id) throws Exception {
        subjects subject = subjectsService.getSubjectById(subject_id);

        subject.setStatus(1);

        subjectsService.updateSubject(subject);
        ResponseData<Object> responseData= new ResponseData<>( null,"删除成功", 200);
        return responseData;
    }


}
