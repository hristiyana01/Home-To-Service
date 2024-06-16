package app.services.implementations;

import app.dtos.CategoryDto;
import app.models.Category;
import app.models.Post;
import app.repositories.CategoryRepository;
import app.repositories.PostRepository;
import app.services.CategoryService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDto;
    }

    @Override
    public CategoryDto getById(Integer categoryId) throws NotFoundException {
        var cat = categoryRepository.findById(categoryId);
        if(cat.isEmpty()){
            throw new NotFoundException("Category with id " + categoryId +" not found.");
        }

        var category = cat.get();
        return mapToCategoryDto(category);
    }


}
