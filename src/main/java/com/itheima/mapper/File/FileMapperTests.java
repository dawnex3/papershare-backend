package com.itheima.mapper.File;

import com.itheima.exception.NotFoundException;
import com.itheima.mapper.User.UserMapper;
import com.itheima.model.File.File;
import org.bson.types.Binary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

@SpringBootTest
class FileMapperTests {
    @Autowired
    private FileMapper fileMapper;

    @Test
    public void addFile() throws Exception {
        File file = new File();
        file.setId(1);
        file.setName("test.pdf");
        Calendar calendar = Calendar.getInstance();
        file.setTime(calendar.getTime());
        file.setHash("12121");
        file.setUploader(1120202000);
        file.setPaper_id(100);
        file.setType(1);
        file.setData(new Binary(fileToByte("C:\\Users\\32854\\Desktop\\Web\\index.js")));

        fileMapper.addFile(file);
    }
    @Test
    public void getOneFile() throws Exception {
        return ;
    }
    public static byte[] fileToByte(String filePath) throws IOException {
        java.io.File file = new java.io.File(filePath);
        byte[] bytes = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fis.read(bytes);
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }finally{
            fis.close();
        }
        return bytes;
    }
}
