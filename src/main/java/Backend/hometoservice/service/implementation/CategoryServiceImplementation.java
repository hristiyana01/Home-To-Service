package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.repository.CategoryRepository;
import Backend.hometoservice.service.CategoryService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImplementation implements CategoryService {
     private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
        categoryRepository.save(category);
        return category;
    }
    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> mapToCategoryDto(category)).collect(Collectors.toList());
    }

    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
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

    @Override
    public Category updateCategory(Integer categoryId, CategoryDto categoryDto) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()) {
            throw new NotFoundException("Post with id " + categoryId +" not found.");
        }

        Category category = categoryOptional.get();

            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            categoryRepository.save(category);

        return category;
    }
}
