package Backend.hometoservice.service;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);

    List<CategoryDto> findAllCategories();

    CategoryDto getById(Integer categoryId) throws NotFoundException;
    Category updateCategory(Integer categoryId, CategoryDto categoryDto) throws NotFoundException;
}
