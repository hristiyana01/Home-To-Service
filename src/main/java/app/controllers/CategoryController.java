package app.controllers;

import app.dtos.CategoryDto;
import app.models.Category;
import app.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary= "Gets category by ID")
    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) throws NotFoundException {
        CategoryDto category = categoryService.getById(categoryId);
        return ResponseEntity.ok(category);
    }
}
