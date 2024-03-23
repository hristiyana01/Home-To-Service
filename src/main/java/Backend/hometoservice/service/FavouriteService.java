package Backend.hometoservice.service;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;

public interface FavouriteService {
    Favourites addFavouritePost(AddFavouritesDto addFavouritesDto);
}
