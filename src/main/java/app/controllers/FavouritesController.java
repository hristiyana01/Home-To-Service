package app.controllers;

import app.dtos.AddFavouritesDto;
import app.dtos.post.FavoritePostsToCheckDto;
import app.dtos.post.PostsToFavoriteMappingsResultDto;
import app.dtos.post.TogglePostAsFavoriteDto;
import app.models.Favourites;
import app.services.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@AllArgsConstructor
public class FavouritesController {
    private final FavouriteService favouriteService;

    @PostMapping("/add")
    public ResponseEntity<Favourites> addFavouritesPost(@RequestBody AddFavouritesDto favourite) {
        Favourites favourites = favouriteService.addFavouritePost(favourite);
        return ResponseEntity.ok().body(favourites);
    }

    @GetMapping("/get-all-by-user/{userId}")
    public ResponseEntity<List<Favourites>> getAllUserFavouritesPosts(@PathVariable Integer userId) {
        var favouritePosts = favouriteService.getAllUserFavouritePosts(userId);
        return ResponseEntity.ok(favouritePosts);
    }

    @DeleteMapping("/remove/{favId}")
    public ResponseEntity<Favourites> removeFavoritePost(@PathVariable Integer favId) {
        favouriteService.deleteById(favId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/toggle")
    public ResponseEntity<Boolean> ToggleFavoritePost(@RequestBody TogglePostAsFavoriteDto togglePostAsFavorite) {
        var resp = favouriteService.toggleFavouritePost(togglePostAsFavorite);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/check-favourite-posts-for-user")
    public ResponseEntity<PostsToFavoriteMappingsResultDto> CheckFavouritePostsForUser(@RequestBody FavoritePostsToCheckDto favoritePostsToCheck) {
        var result = favouriteService.checkFavoritePosts(favoritePostsToCheck);
        return ResponseEntity.ok(result);
    }
}
