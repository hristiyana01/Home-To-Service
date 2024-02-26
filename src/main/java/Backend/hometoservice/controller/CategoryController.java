package Backend.hometoservice.controller;

import Backend.hometoservice.model.Category;
import Backend.hometoservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/add")
    public String addCategory(@RequestBody Category category) {
        return "addCategory";//category.addCategory(category);
    }
}
