package Backend.hometoservice.controller;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/favourites")
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
    public ResponseEntity<Favourites> removeFavoritePost (@PathVariable Integer favId) {
        favouriteService.deleteById(favId);
        return ResponseEntity.ok().build();
    }

}
