package com.example.youtube.service;


import com.example.youtube.repository.VideoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;

}
