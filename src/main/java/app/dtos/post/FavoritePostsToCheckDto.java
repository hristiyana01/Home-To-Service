package app.dtos.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritePostsToCheckDto {
    private Integer userId;
    private List<Integer> postsToCheck = new ArrayList<Integer>();
}
