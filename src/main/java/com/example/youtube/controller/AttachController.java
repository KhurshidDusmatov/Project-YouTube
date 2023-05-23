package com.example.youtube.controller;

import com.example.youtube.dto.attach.AttachDTO;
import com.example.youtube.dto.attach.AttachRequestDTO;
import com.example.youtube.dto.playlist.PlayListInfoDTO;
import com.example.youtube.service.AttachService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attach")
public class
AttachController {
   @Autowired
   private AttachService attachService;
   @PostMapping("/public/upload")
   public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file) {
      AttachDTO fileName = attachService.upload(file);
      return ResponseEntity.ok().body(fileName);
   }
   @GetMapping("/public/getById/{id}")
   public ResponseEntity<AttachRequestDTO> getById(@PathVariable("id") String id){
      AttachRequestDTO attachById = attachService.getAttachById(id);
      return ResponseEntity.ok(attachById);
   }

   @GetMapping("/public/download/{fileName}")
   public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
      Resource file = attachService.download(fileName);
      return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + file.getFilename() + "\"").body(file);
   }
   @DeleteMapping("/private/deleteById/{id}")
   public ResponseEntity<?> deleteById(@PathVariable("id") String id){
      return ResponseEntity.ok(attachService.delete(id));
   }

   @GetMapping("/private/pagination/")
   public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "4") int size){
      Page<AttachDTO> pagination = attachService.paginationWithDate(page, size);
      return ResponseEntity.ok(pagination);
   }
}
