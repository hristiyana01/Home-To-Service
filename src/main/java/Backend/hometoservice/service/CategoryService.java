package Backend.hometoservice.service;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
}
