package Backend.hometoservice.service;

import Backend.hometoservice.dto.CategoryDto;
import Backend.hometoservice.model.Category;
import Backend.hometoservice.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
}
