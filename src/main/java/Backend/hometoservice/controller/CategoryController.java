package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") CategoryDto categoryDto, Model model) {
        Category createdCategory = categoryService.addCategory(categoryDto);
        model.addAttribute("category", createdCategory);
        return "category-added"; // Името на HTML файла за изглед
    }
    @GetMapping("/categories")
    public String getAllCategories(Model model) {
         List<CategoryDto> categories = categoryService.findAllCategories();
         model.addAttribute("categories", categories);
         return "categories-list";
    }
    @GetMapping("/get/{categoryId}")
    public String getCategory(@PathVariable Integer categoryId, Model model) {
        Optional<Category> category = categoryService.getById(categoryId);
        return "category-page";
    }
}
