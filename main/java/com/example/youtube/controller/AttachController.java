package com.example.youtube.controller;

import com.example.youtube.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {
   @Autowired
   private AttachService attachService;

}
