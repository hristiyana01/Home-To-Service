package Backend.hometoservice.repository;

import Backend.hometoservice.model.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
    Optional<List<Favourites>> findFavouritesByUserId(Integer userId);
    Optional<List<Favourites>> findFavouritesByUserIdAndPostId(Integer userId, Integer postId);
    Optional<List<Favourites>> findAllByUserIdAndPostIdIn(Integer userId, List<Integer> postId);
}
