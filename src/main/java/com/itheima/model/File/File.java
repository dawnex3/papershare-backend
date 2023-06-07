package com.itheima.model.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Document(collection="files")
public class File {
    //必须指定id列
    @Id
    private Integer id;
    private String name;
    private Integer type;
    private String hash;
    private Integer uploader;
    private Date time;
    private Binary data;
    private Integer paper_id;
    private Integer size;
    private String content_type;

    public File(Integer id, String name, Integer type, String content_type, Integer uploader, Date time, Integer paper_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.uploader = uploader;
        this.time = time;
        this.paper_id = paper_id;
        this.content_type = content_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getUploader() {
        return uploader;
    }

    public void setUploader(Integer uploader) {
        this.uploader = uploader;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Binary getData() {
        return data;
    }

    public void setData(Binary data) {
        this.data = data;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String hash) {
        this.content_type = content_type;
    }
}
