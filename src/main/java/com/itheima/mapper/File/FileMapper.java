package com.itheima.mapper.File;

import com.itheima.exception.NotFoundException;
import com.itheima.model.File.File;

import java.util.List;

public interface FileMapper {
    Integer getMaxId() throws Exception;
    void addFile(File file) throws Exception;
    File getOneFile(Integer fileId) throws Exception;

    List<File> getFileBy(Integer id, Integer uploader, Integer subject_id, Integer type) throws Exception;

    void deleteById(Integer file_id) throws NotFoundException;
}
