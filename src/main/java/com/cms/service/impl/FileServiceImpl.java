package com.cms.service.impl;

import com.cms.entity.FileRecord;
import com.cms.mapper.FileMapper;
import com.cms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public FileRecord saveFileRecord(FileRecord fileRecord) {
        fileRecord.setUploadTime(new Date());
        fileMapper.insert(fileRecord);
        return fileRecord;
    }

    @Override
    public FileRecord getFileById(Long id) {
        return fileMapper.selectById(id);
    }

    @Override
    public List<FileRecord> getAllFiles() {
        return fileMapper.selectAll();
    }

    @Override
    public void deleteFile(Long id) {
        fileMapper.deleteById(id);
    }
}
