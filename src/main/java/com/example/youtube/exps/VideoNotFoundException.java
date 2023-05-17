package com.example.youtube.exps;

public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException(String message) {
        super(message);
    }
}
