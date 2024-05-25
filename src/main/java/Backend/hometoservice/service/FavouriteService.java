package Backend.hometoservice.service;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.dto.FavoritePostsToCheckDto;
import Backend.hometoservice.dto.PostsToFavoriteMappingsResultDto;
import Backend.hometoservice.dto.TogglePostAsFavoriteDto;
import Backend.hometoservice.model.Favourites;

import java.util.List;

public interface FavouriteService {
    Favourites addFavouritePost(AddFavouritesDto addFavouritesDto);
    List<Favourites> getAllUserFavouritePosts(Integer userId);
    void deleteById(Integer favId);
    Boolean toggleFavouritePost(TogglePostAsFavoriteDto togglePostAsFavorite);
    PostsToFavoriteMappingsResultDto checkFavoritePosts(FavoritePostsToCheckDto favoritePostsToCheck);
}
