package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.dto.FavoritePostsToCheckDto;
import Backend.hometoservice.dto.PostsToFavoriteMappingsResultDto;
import Backend.hometoservice.dto.TogglePostAsFavoriteDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.repository.FavouritesRepository;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavouritesServiceImplementation implements FavouriteService {
    private final FavouritesRepository favouritesRepository;

    public Favourites addFavouritePost(AddFavouritesDto addFavouritesDto) {
        var isFavorite = !favouritesRepository
                .findFavouritesByUserIdAndPostId(addFavouritesDto.getUserId(), addFavouritesDto.getPostId())
                .get().isEmpty();

        if(isFavorite) {
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
        if(!(favouritesRepository.existsById(favId))) {
            System.out.println("Post not added to favorites");
        }
        favouritesRepository.deleteById(favId);

        //TODO: implement message for deleting non-existent comment
    }


    public Boolean toggleFavouritePost(TogglePostAsFavoriteDto togglePostAsFavorite) {
        Optional<List<Favourites>> favoritePosts = favouritesRepository
                .findFavouritesByUserIdAndPostId(togglePostAsFavorite.getUserId(), togglePostAsFavorite.getPostId());

        boolean isAlreadyAddedAsFavorite = favoritePosts.isPresent() && !favoritePosts.get().isEmpty();

        if(isAlreadyAddedAsFavorite) {
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
    public PostsToFavoriteMappingsResultDto checkFavoritePosts(FavoritePostsToCheckDto favouritePostsToCheck) {
        var postsToFavouriteMap = favouritePostsToCheck.getPostsToCheck().stream().distinct().collect(Collectors.toMap(Function.identity(), postId -> false));
        var userFavoritePostsResultOpt = this.favouritesRepository.findAllByUserIdAndPostIdIn(favouritePostsToCheck.getUserId(), favouritePostsToCheck.getPostsToCheck());

        var userFavoritePostsResult = userFavoritePostsResultOpt.get();
        for(Favourites favourite : userFavoritePostsResult) {
            postsToFavouriteMap.put(favourite.getPostId(), true);
        }

        var result = new PostsToFavoriteMappingsResultDto();
        result.setFavoritePostsMappings(postsToFavouriteMap);
        return result;
    }

    public AddFavouritesDto mapFavorite(Favourites fav){
        return AddFavouritesDto.builder()
                .postId(fav.getPostId())
                .userId(fav.getUserId())
                .build();
    }

}
