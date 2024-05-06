package Backend.hometoservice.service;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FavouriteService {
    Favourites addFavouritePost(AddFavouritesDto addFavouritesDto);
    List<Integer> getAllUserFavouritePosts(Integer userId);
}
