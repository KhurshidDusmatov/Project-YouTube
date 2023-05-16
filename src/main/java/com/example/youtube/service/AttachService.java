package com.example.youtube.service;

import com.example.youtube.dto.AttachDTO;
import com.example.youtube.entity.AttachEntity;
import com.example.youtube.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;
    @Value("attaches")
    private String folderName;
    @Value("${server.host}")
    private String serverHost;
    public AttachDTO upload(MultipartFile file) {
        try {
            String pathFolder = getYmDString(); // 2022/04/23
            File folder = new File("attaches/" + pathFolder);  // attaches/2023/04/26
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            String extension = getExtension(file.getOriginalFilename());

            AttachEntity attachEntity = new AttachEntity();
            attachEntity.setId(UUID.randomUUID().toString());
            attachEntity.setCreatedDate(LocalDateTime.now());
            attachEntity.setExtension(extension);
            attachEntity.setSize(file.getSize());
            attachEntity.setPath(pathFolder);
            attachEntity.setOriginalName(file.getOriginalFilename());
            attachRepository.save(attachEntity);

            Path path = Paths.get("attaches/" + pathFolder + "/" + attachEntity.getId() + "." + extension);
            // attaches/2023/04/26/uuid().jpg
            Files.write(path, bytes);

            AttachDTO dto = new AttachDTO();
            dto.setId(attachEntity.getId());
            dto.setOriginalName(file.getOriginalFilename());
            dto.setUrl(serverHost + "/api/v1/attach/open/" + attachEntity.getId() + "." + extension);
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getExtension(String name){
        int lastIndex= name.lastIndexOf(".");
        return name.substring(lastIndex+1);
    }
    public String getYmDString() {
        int year = LocalDate.EPOCH.getYear();
        int month =LocalDate.EPOCH.getMonthValue()+1;
        int day = LocalDate.EPOCH.getDayOfMonth();
        return year + "/" + month + "/" + day; // 2022/04/23
    }
}
