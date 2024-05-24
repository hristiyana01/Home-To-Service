package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.repository.FavouritesRepository;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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


    public Integer toggleFavouritePost(AddFavouritesDto addFavouritesDto) {
        Optional<List<Favourites>> favoritePosts = favouritesRepository
                .findFavouritesByUserIdAndPostId(addFavouritesDto.getUserId(), addFavouritesDto.getPostId());

        boolean isAlreadyAddedAsFavorite = favoritePosts.isPresent() && !favoritePosts.get().isEmpty();

        if(isAlreadyAddedAsFavorite) {
            Integer favoriteId = favoritePosts.get().getFirst().getId();
            favouritesRepository.deleteById(favoriteId);
            return favoriteId;
        }

        Favourites favourite = Favourites.builder()
                .userId(addFavouritesDto.getUserId())
                .postId(addFavouritesDto.getPostId())
                .favoriteDate(Instant.now())
                .build();
        Favourites addedFavorite = favouritesRepository.save(favourite);

        return addedFavorite.getId();
    }

    public AddFavouritesDto mapFavorite(Favourites fav){
        return AddFavouritesDto.builder()
                .postId(fav.getPostId())
                .userId(fav.getUserId())
                .build();
    }

}
