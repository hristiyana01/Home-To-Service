package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.repository.CategoryRepository;
import Backend.hometoservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImplementation implements CategoryService {
     private CategoryRepository categoryRepository;

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

    @Override
    public Optional<Category> getById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
        return categoryDto;
    }


}
