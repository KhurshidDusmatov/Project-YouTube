package com.example.youtube.entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name ="created_date" )
    private LocalDateTime createdDate=LocalDateTime.now();
}
