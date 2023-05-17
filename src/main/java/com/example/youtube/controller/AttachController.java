package com.example.youtube.controller;

import com.example.youtube.dto.attach.AttachDTO;
import com.example.youtube.dto.attach.AttachRequestDTO;
import com.example.youtube.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {
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
}
