package com.example.youtube.controller;

import com.example.youtube.dto.AttachDTO;
import com.example.youtube.service.AttachService;
import com.example.youtube.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
