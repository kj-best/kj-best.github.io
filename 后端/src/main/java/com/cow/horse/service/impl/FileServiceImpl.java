package com.cow.horse.service.impl;

import com.cow.horse.service.FileService;
import com.cow.horse.common.utils.PathUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    public boolean uploadFile(MultipartFile multipartFile,String fileName)throws IOException {
        File fileDir = new File(PathUtils.getClassLoadRootPath() + "/pic");
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
//        System.out.println(fileDir.getAbsolutePath() +"/"+fileName);
        File file = new File(fileDir.getAbsolutePath() +"/"+fileName);
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
