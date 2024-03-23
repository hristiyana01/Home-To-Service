package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.repository.FavouritesRepository;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class FavouritesServiceImplementation implements FavouriteService {
    private final FavouritesRepository favouritesRepository;

    public Favourites addFavouritePost(AddFavouritesDto addFavouritesDto) {
        Favourites favourite = Favourites.builder()
                .userId(addFavouritesDto.getUserId())
                .postId(addFavouritesDto.getPostId())
                .favoriteDate(Instant.now())
                .build();
        return favouritesRepository.save(favourite);
    }
}
