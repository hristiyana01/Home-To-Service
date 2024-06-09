package app.services;

import app.dtos.AddFavouritesDto;
import app.dtos.post.FavoritePostsToCheckDto;
import app.dtos.post.PostsToFavoriteMappingsResultDto;
import app.dtos.post.TogglePostAsFavoriteDto;
import app.models.Favourites;

import java.util.List;

public interface FavouriteService {
    Favourites addFavouritePost(AddFavouritesDto addFavouritesDto);

    List<Favourites> getAllUserFavouritePosts(Integer userId);

    void deleteById(Integer favId);

    Boolean toggleFavouritePost(TogglePostAsFavoriteDto togglePostAsFavorite);

    PostsToFavoriteMappingsResultDto checkFavoritePosts(FavoritePostsToCheckDto favoritePostsToCheck);
}
