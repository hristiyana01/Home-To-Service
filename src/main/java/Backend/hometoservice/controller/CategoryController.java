package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto) {
        var createdCategory = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();

        return categories;
    }
    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable Integer categoryId) {
        Optional<Category> category = categoryService.getById(categoryId);
        return ResponseEntity.ok(category);
    }
}
