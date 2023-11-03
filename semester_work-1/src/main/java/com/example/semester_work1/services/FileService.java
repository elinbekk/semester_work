package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.ProfilePhotoDaoImpl;
import com.example.semester_work1.models.ProfilePhoto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

public class FileService {
    private String path = "C:\\javaee\\semester_works\\semester_work-1\\src\\main\\webapp\\img\\";
    private ProfilePhotoDaoImpl profilePhotoDao;

    public FileService(ProfilePhotoDaoImpl profilePhotoDao) {
        this.profilePhotoDao = profilePhotoDao;
    }

    public void upload(String userId, String filename, Long size, String contentType, InputStream fileInputStream) throws IOException {
        ProfilePhoto photo = new ProfilePhoto(
                userId,
                filename,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(fileInputStream, Paths.get(path + filename));
            profilePhotoDao.save(photo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
