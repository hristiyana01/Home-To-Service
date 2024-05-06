package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.service.CategoryService;
import io.swagger.models.Response;
import javassist.NotFoundException;
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
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return  ResponseEntity.ok(categoryService.findAllCategories());
    }
    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) throws NotFoundException {
        CategoryDto category = categoryService.getById(categoryId);
        return ResponseEntity.ok(category);
    }
    //only for sysadmin
    //TODO: who updates the category -> ONLY SYSADMIN !!!
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId) throws NotFoundException {
        Category category = categoryService.updateCategory(categoryId);
        return ResponseEntity.ok(category);
    }
}
