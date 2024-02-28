package Backend.hometoservice.controller;

import Backend.hometoservice.model.Favourites;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/favourites")
@AllArgsConstructor
public class FavouritesController {

//    @PostMapping("/add")
//    public ResponseEntity<Favourites> addFavourite(@ResponseBody Favourites favourites) {
//        return ResponseEntity.ok().body(favourites);
//    }
}
