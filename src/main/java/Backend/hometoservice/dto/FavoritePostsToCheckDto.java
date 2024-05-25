package Backend.hometoservice.dto;

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


/*
Request
{
user: 1,
postsToCheck: [5, 12, 15, ...] (postIds)
}*/
/*
Result:
{
5: false,
12: true,
15: false
}
*/