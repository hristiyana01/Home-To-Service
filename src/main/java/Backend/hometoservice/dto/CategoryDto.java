package Backend.hometoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
}
