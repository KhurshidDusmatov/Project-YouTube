package com.example.youtube.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {

    private String id;
    private String name;
    private LocalDateTime createdDate;
}
