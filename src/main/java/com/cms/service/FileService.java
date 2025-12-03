package com.cms.service;

import com.cms.entity.FileRecord;
import java.util.List;

public interface FileService {
    FileRecord saveFileRecord(FileRecord fileRecord);

    FileRecord getFileById(Long id);

    List<FileRecord> getAllFiles();

    void deleteFile(Long id);
}
