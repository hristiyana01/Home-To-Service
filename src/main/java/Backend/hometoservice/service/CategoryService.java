package Backend.hometoservice.service;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
    List<CategoryDto> findAllCategories();
    Optional<Category> getById(Integer categoryId);
}
