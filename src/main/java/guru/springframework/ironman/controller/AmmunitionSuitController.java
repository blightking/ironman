package guru.springframework.ironman.controller;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.service.AmmunitionSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/complete")
public class AmmunitionSuitController {
    @Autowired
    private AmmunitionSuitService ammunitionSuitService;

    @RequestMapping(value = "/suit/{suitId}/with/{ammoId}/new", method = RequestMethod.POST)
    public ResponseEntity<Boolean> completeAmmunitionSuit(
            @PathVariable("suitId") Suit suit,
            @PathVariable("ammoId") Ammunition ammunition,
            @RequestBody Map<String, Object> objectMap
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (suit == null || ammunition == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(ammunitionSuitService.isExistSuitAmmunition(suit, ammunition)){
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        AmmunitionSuit ammunitionSuit = new AmmunitionSuit();
        ammunitionSuit.setAmmunition(ammunition);
        ammunitionSuit.setSuit(suit);
        ammunitionSuit.setCount((Integer) objectMap.get("count"));
        ammunitionSuitService.addSuitAmmunition(ammunitionSuit);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

}
