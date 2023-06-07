package com.itheima.service.File;

import com.itheima.mapper.File.FileMapper;
import com.itheima.mapper.Paper.PaperMapper;
import com.itheima.model.File.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImp implements FileService{
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Integer getMaxId() throws Exception {
        return fileMapper.getMaxId();
    }

    @Override
    public List<File> getFileBy(String id, String uploader, String subject_id, String type) throws Exception {
        return fileMapper.getFileBy(id==null?null:Integer.valueOf(id), uploader==null?null:Integer.valueOf(uploader),
                subject_id==null?null:Integer.valueOf(subject_id), type==null?null:Integer.valueOf(type));
    }

    @Override
    public void addFile(File file) throws Exception {
        fileMapper.addFile(file);
    }

    @Override
    public File getOneFile(Integer fileId) throws Exception {
        return fileMapper.getOneFile(fileId);
    }

    @Override
    public void deleteById(Integer file_id) throws Exception {
        fileMapper.deleteById(file_id);
    }
}
