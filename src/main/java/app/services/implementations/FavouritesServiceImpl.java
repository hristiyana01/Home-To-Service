package app.services.implementations;

import app.dtos.AddFavouritesDto;
import app.dtos.post.FavoritePostsToCheckDto;
import app.dtos.post.PostsToFavoriteMappingsResultDto;
import app.dtos.post.TogglePostAsFavoriteDto;
import app.models.Favourites;
import app.repositories.FavouritesRepository;
import app.services.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavouritesServiceImpl implements FavouriteService {
    private final FavouritesRepository favouritesRepository;

    @Override
    public Favourites addFavouritePost(AddFavouritesDto addFavouritesDto) {
        var isFavorite = !favouritesRepository
                .findFavouritesByUserIdAndPostId(addFavouritesDto.getUserId(), addFavouritesDto.getPostId())
                .get().isEmpty();

        if (isFavorite) {
            throw new IllegalArgumentException("Post already added as favorite.");
        }

        Favourites favourite = Favourites.builder()
                .userId(addFavouritesDto.getUserId())
                .postId(addFavouritesDto.getPostId())
                .favoriteDate(Instant.now())
                .build();

        return favouritesRepository.save(favourite);
    }

    @Override
    public List<Favourites> getAllUserFavouritePosts(Integer userId) {
        var favoritesByIdOpt = favouritesRepository.findFavouritesByUserId(userId);
        return favoritesByIdOpt.get();
    }

    @Override
    public void deleteById(Integer favId) {
        if (!(favouritesRepository.existsById(favId))) {
            System.out.println("Post not added to favorites");
        }
        favouritesRepository.deleteById(favId);
    }

    @Override
    public Boolean toggleFavouritePost(TogglePostAsFavoriteDto togglePostAsFavorite) {
        Optional<List<Favourites>> favoritePosts = favouritesRepository
                .findFavouritesByUserIdAndPostId(togglePostAsFavorite.getUserId(), togglePostAsFavorite.getPostId());

        boolean isAlreadyAddedAsFavorite = favoritePosts.isPresent() && !favoritePosts.get().isEmpty();

        if (isAlreadyAddedAsFavorite) {
            Integer favoriteId = favoritePosts.get().getFirst().getId();
            favouritesRepository.deleteById(favoriteId);
            return false;
        }

        Favourites favourite = Favourites.builder()
                .userId(togglePostAsFavorite.getUserId())
                .postId(togglePostAsFavorite.getPostId())
                .favoriteDate(Instant.now())
                .build();
        Favourites addedFavorite = favouritesRepository.save(favourite);

        return true;
    }

    @Override
    public PostsToFavoriteMappingsResultDto checkFavoritePosts(FavoritePostsToCheckDto favoritePostsToCheck) {
        var postsToFavouriteMap = favoritePostsToCheck.getPostsToCheck().stream().distinct().collect(Collectors.toMap(Function.identity(), postId -> false));
        var userFavoritePostsResultOpt = this.favouritesRepository.findAllByUserIdAndPostIdIn(favoritePostsToCheck.getUserId(), favoritePostsToCheck.getPostsToCheck());

        var userFavoritePostsResult = userFavoritePostsResultOpt.get();
        for (Favourites favourite : userFavoritePostsResult) {
            postsToFavouriteMap.put(favourite.getPostId(), true);
        }

        var result = new PostsToFavoriteMappingsResultDto();
        result.setFavoritePostsMappings(postsToFavouriteMap);
        return result;
    }
}
