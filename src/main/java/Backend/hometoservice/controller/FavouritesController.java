package Backend.hometoservice.controller;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.repository.FavouritesRepository;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
