package Backend.hometoservice.repository;

import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
    Optional<List<Favourites>> findAllByUserId(Integer userId);
    Optional<List<Favourites>> findFavouritesByUserIdAndPostId(Integer userId, Integer postId);

}
