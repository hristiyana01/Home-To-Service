package app.services;

import app.dtos.CategoryDto;
import app.models.Category;
import javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    void addCategory(Category category);
    CategoryDto getById(Integer categoryId) throws NotFoundException;

}
