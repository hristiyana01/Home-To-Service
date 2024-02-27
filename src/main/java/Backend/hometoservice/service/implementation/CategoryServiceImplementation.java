package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.repository.CategoryRepository;
import Backend.hometoservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
