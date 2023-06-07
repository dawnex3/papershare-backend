package com.itheima.controller.File;

import com.itheima.controller.ResponseData;
import com.itheima.model.Course.Courses;
import com.itheima.model.File.File;
import com.itheima.model.User.User;
import com.itheima.service.Course.coursesService;
import com.itheima.service.File.FileService;
import com.itheima.service.Paper.PaperService;
import com.itheima.service.User.UserService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private UserService userService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private FileService fileService;
    @GetMapping
    public ResponseData<List<File>> getFileInfo(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String uploader = request.getParameter("uploader");
        String paper_id = request.getParameter("paper_id");
        String type = request.getParameter("type");

        List<File> files = fileService.getFileBy(id,uploader,paper_id,type);
        return new ResponseData<>(files, "获得文件信息", 200);
    }

    @GetMapping("/{file_id}/download")
    public ResponseEntity<Object> downloadFile(@PathVariable Integer file_id) throws Exception {
        File file = fileService.getOneFile(file_id);
        if (file != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")

                    .body(file.getData().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(null, "文件不存在", 404));
        }
    }

    @GetMapping("/{file_id}/view")
    public ResponseEntity<Object> viewFile(@PathVariable Integer file_id) throws Exception {
        File file = fileService.getOneFile(file_id);
        if (file != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.getContent_type())
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .header("Connection", "close")
                    .body(file.getData().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(null, "文件不存在", 404));
        }
    }

    @PostMapping
    public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("type") Integer type,@RequestParam("uploader") Integer uploader,@RequestParam("paper_id") Integer paper_id) throws Exception {
        try {
            int max_id = fileService.getMaxId();
            Calendar calendar = Calendar.getInstance();
            File f = new File(max_id+1, file.getOriginalFilename(),type, file.getContentType(), uploader, calendar.getTime(),paper_id);
            f.setData(new Binary(file.getBytes()));
            f.setSize((int) file.getSize());
            fileService.addFile(f);
            File fileInfo = fileService.getFileBy(String.valueOf(max_id+1),null,null,null).get(0);
            if(type==1){
                paperService.addAnswer(paper_id, max_id+1);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseData<>(fileInfo, "上传成功", 200));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData<>(null, "上传失败", 400));
        }
    }

    @DeleteMapping()
    public ResponseData<Object> deleteFile(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String uploader = request.getParameter("uploader");
        String paper_id = request.getParameter("paper_id");
        String type = request.getParameter("type");

        List<File> files = fileService.getFileBy(id,uploader,paper_id,type);
        return new ResponseData<>(files, "获得文件信息", 200);
    }
    @DeleteMapping("/{file_id}")
    public ResponseData<Object> deleteFile(@PathVariable Integer file_id) throws Exception{
        fileService.deleteById(file_id);
        return new ResponseData<>(null, "删除文件成功", 200);
    }
}
