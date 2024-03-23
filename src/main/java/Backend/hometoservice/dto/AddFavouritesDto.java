package Backend.hometoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFavouritesDto {
    private Integer userId;
    private Integer postId;
}
