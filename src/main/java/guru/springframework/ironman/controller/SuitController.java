package guru.springframework.ironman.controller;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.service.AmmunitionSuitService;
import guru.springframework.ironman.service.SuitService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suit")
public class SuitController {
    private final SuitService suitService;
    private final AmmunitionSuitService ammunitionSuitService;

    public SuitController(SuitService suitService, AmmunitionSuitService ammunitionSuitService) {
        this.suitService = suitService;
        this.ammunitionSuitService = ammunitionSuitService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Suit>> getAllSuits() {
        List<Suit> suits = suitService.findAll();
        if (suits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suits, HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Suit> saveSuit(@RequestBody @Valid Suit suit) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (suit == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        suitService.save(suit);

        return new ResponseEntity<>(suit, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Suit> deleteSuit(@PathVariable("id") Long id) {
        Suit suit = suitService.getDataById(id);
        if (suit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        suitService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Suit> updateSuit(@PathVariable("id") Long id, @RequestBody @Valid Suit newSuit, UriComponentsBuilder builder) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Suit suit = suitService.getDataById(id);
        if (suit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newSuit == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        suit.setModel(newSuit.getModel());
        suitService.update(suit);
        return new ResponseEntity<>(suit, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Suit> getSuit(@PathVariable("id") Long suitId) {
        if (suitId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suit suit = suitService.getDataById(suitId);
        if (suit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suit, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Suit> getSuitByModel(@RequestParam(name = "model", required = false) String model) {
        Suit suit = suitService.findSuitByModel(model);
        List<Ammunition> ammunitionList = ammunitionSuitService.loadAllAmmunitionBySuit(suit);
        if (suit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double percentSuitComplete = ammunitionSuitService.percentCompleteSuitWithAmmunition(suit.getId());
        if (percentSuitComplete == 0) {
            System.out.println("Костюм на будущее)");
        } else {
            for (Ammunition ammunition : ammunitionList) {
                System.out.println(ammunition.getName());
                System.out.println(ammunition.getDescription());

                if (percentSuitComplete < 100) {
                    AmmunitionSuit ammunitionSuit = ammunitionSuitService.getAmmoSuitBySuitIdAndAmmoId(suit, ammunition);
                    double diff = ammunitionSuitService.countLackOfAmmunition(ammunitionSuit);
                    if (diff >= 0) {
                        System.out.println("Lack of ammunition count = " + diff);
                    } else {
                        System.out.println("Ammunition: " + ammunitionSuit.getAmmunition().getName() + " = Norm");
                    }
                }
                System.out.println("--------------------------");
            }
        }
        return new ResponseEntity<>(suit, HttpStatus.OK);
    }

}
