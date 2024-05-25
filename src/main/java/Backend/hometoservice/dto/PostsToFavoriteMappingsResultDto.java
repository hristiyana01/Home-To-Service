package Backend.hometoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsToFavoriteMappingsResultDto {
    private Map<Integer, Boolean> favoritePostsMappings = new HashMap<Integer, Boolean>();
}
