package com.cms.mapper;

import com.cms.entity.FileRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    int insert(FileRecord fileRecord);

    FileRecord selectById(Long id);

    List<FileRecord> selectAll();

    int deleteById(Long id);
}
