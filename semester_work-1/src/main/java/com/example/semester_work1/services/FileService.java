package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.ProfilePhotoDaoImpl;
import com.example.semester_work1.models.ProfilePhoto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

public class FileService {
    private String path;
    private ProfilePhotoDaoImpl profilePhotoDao;

    public FileService(ProfilePhotoDaoImpl profilePhotoDao) {
        this.profilePhotoDao = profilePhotoDao;
    }

    public boolean upload(UUID userId, String filename, Long size, String contentType, InputStream fileInputStream) {
        ProfilePhoto photo = new ProfilePhoto(
                userId,
                filename,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(fileInputStream, Paths.get(path + filename));
            if(profilePhotoDao.getPhotoByUserId(userId) != null){
                profilePhotoDao.delete(userId);
            }
            profilePhotoDao.save(photo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        File file = new File(path + filename);
        return file.exists();
    }

    public void setPath(String path) {
        this.path = path + "/img/";
    }
}
