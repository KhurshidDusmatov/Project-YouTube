package com.example.youtube.controller;

import com.example.youtube.dto.category.CategoryRequestDTO;
import com.example.youtube.service.CategoryService;
import com.example.youtube.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/private")
    public ResponseEntity<?> create(@RequestBody CategoryRequestDTO dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/private/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestParam("name") String name) {
        return ResponseEntity.ok(categoryService.update(id, name));
    }
    @DeleteMapping("/private/{id}")
    public ResponseEntity<?>delete (@PathVariable("id")String id){
        return ResponseEntity.ok(categoryService.delete(id));
    }

    @GetMapping("/public")
    public ResponseEntity<List<CategoryRequestDTO>>getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }
}
