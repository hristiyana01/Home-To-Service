package Backend.hometoservice.controller;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Integer>> getAllUserFavouritesPosts(@PathVariable Integer userId) {
        var users = favouriteService.getAllUserFavouritePosts(userId);
        return ResponseEntity.ok(users);
    }

}
