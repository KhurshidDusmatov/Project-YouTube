package com.example.youtube.service;

import com.example.youtube.dto.attach.AttachDTO;
import com.example.youtube.dto.attach.AttachRequestDTO;
import com.example.youtube.dto.attach.AttachResponseDTO;
import com.example.youtube.entity.AttachEntity;
import com.example.youtube.exps.ItemNotFoundException;
import com.example.youtube.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
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

    public AttachRequestDTO getAttachById(String id) {
        AttachEntity attachEntity = get(id);
        AttachRequestDTO dto = new AttachRequestDTO();
        dto.setId(attachEntity.getId());
        dto.setOriginalName(attachEntity.getOriginalName());
        String extension = getExtension(attachEntity.getOriginalName());
        dto.setUrl(serverHost + "/api/v1/attach/open/" + attachEntity.getId() + "." + extension);
        return dto;
    }

    public Resource download(String fileName) {
        try {
//            int lastIndex = fileName.lastIndexOf(".");
//            String id = fileName.substring(0, lastIndex+1);
            AttachEntity attachEntity = get(fileName);

            Path file = Paths.get("attaches/" + attachEntity.getPath() + "/" + fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private AttachEntity get(String id) {
        Optional<AttachEntity> byId = attachRepository.findById(id);
        if (byId == null) {
            throw new ItemNotFoundException("Attach not found");
        }
        return byId.get();
    }

    public String getExtension(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    public String getYmDString() {
        int year = LocalDate.EPOCH.getYear();
        int month = LocalDate.EPOCH.getMonthValue() + 1;
        int day = LocalDate.EPOCH.getDayOfMonth();
        return year + "/" + month + "/" + day; // 2022/04/23
    }

    public AttachResponseDTO toResponseDTO(AttachEntity entity) {
        AttachResponseDTO dto = new AttachResponseDTO();
        dto.setId(entity.getId());
        dto.setUrl(entity.getPath());
        return dto;
    }
}
