package Backend.hometoservice.controller;

import Backend.hometoservice.dto.AddFavouritesDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.repository.FavouritesRepository;
import Backend.hometoservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/favourites")
@AllArgsConstructor
public class FavouritesController {
private final FavouriteService favouriteService;
    @PostMapping("/add")
    public ResponseEntity<Favourites> addFavouritesPost(@ModelAttribute AddFavouritesDto favourite, Model model) {
        Favourites favourites = favouriteService.addFavouritePost(favourite);
        model.addAttribute("favourites", favourites);
        return ResponseEntity.ok(favourites);
    }
    @DeleteMapping("/delete/{favouriteId}")
    public ResponseEntity<String> deleteFavouritePost(@PathVariable Integer favouriteId) {
        favouriteService.deleteFavouritePost(favouriteId);
        return ResponseEntity.ok().body("Favourite post deleted successfully");
    }
//user favourites posts -> in Post Controller
//    @GetMapping("/getAll/{userId}")
//    public String getAllUserFavourites(@PathVariable Integer userId, Model model) {
//        Optional<List<Favourites>> favouritesDtos = favouriteService.getAllUserFavourites(userId);
//        model.addAttribute("userFavourites",favouritesDtos);
//        return "user-favourites";
//    }

}
