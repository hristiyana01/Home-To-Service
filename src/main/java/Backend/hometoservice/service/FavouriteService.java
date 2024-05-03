package Backend.hometoservice.service;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;

import java.util.List;
import java.util.Optional;

public interface FavouriteService {
    Favourites addFavouritePost(AddFavouritesDto addFavouritesDto);

    void deleteFavouritePost(Integer favouriteId);
    Optional<List<Favourites>> getAllUserFavourites(Integer userId);
}
