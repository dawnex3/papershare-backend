package com.itheima.service.File;

import com.itheima.model.File.File;

import java.util.List;

public interface FileService {
    Integer getMaxId() throws Exception;
    List<File> getFileBy(String id,String uploader,String subject_id,String type) throws Exception;
    void addFile(File file) throws Exception;
    File getOneFile(Integer fileId) throws Exception;

    void deleteById(Integer file_id) throws Exception;
}
