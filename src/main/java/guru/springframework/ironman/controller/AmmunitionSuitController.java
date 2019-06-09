package guru.springframework.ironman.controller;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.service.AmmunitionService;
import guru.springframework.ironman.service.AmmunitionSuitService;
import guru.springframework.ironman.service.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/complete")
public class AmmunitionSuitController {

    @Autowired
    private AmmunitionSuitService ammunitionSuitService;

    @Autowired
    private AmmunitionService ammunitionService;

    @Autowired
    private SuitService suitService;

    @RequestMapping(value = "/suit/{suitId}/with/{ammoId}/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> completeAmmunitionSuit(
            @PathVariable("suitId") String suitId,
            @PathVariable("ammoId") String ammunitionId,
            @RequestBody Map<String, Object> objectMap
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Suit suit = suitService.getDataById(Long.parseLong(suitId));
        Ammunition ammunition = ammunitionService.getDataById(Long.parseLong(ammunitionId));

        if (suit == null || ammunition == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (ammunitionSuitService.isExistSuitAmmunition(suit, ammunition)) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        AmmunitionSuit ammunitionSuit = new AmmunitionSuit();
        ammunitionSuit.setId(Long.valueOf((Integer) objectMap.get("id")));
        ammunitionSuit.setAmmunition(ammunition);
        ammunitionSuit.setSuit(suit);
        ammunitionSuit.setCount((Integer) objectMap.get("count"));
        ammunitionSuitService.addSuitAmmunition(ammunitionSuit);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

}
