package guru.springframework.ironman.controller;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.service.AmmunitionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ammo")
public class AmmunitionController {
    private final AmmunitionService ammunitionService;

    public AmmunitionController(AmmunitionService ammunitionService) {
        this.ammunitionService = ammunitionService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ammunition>> getAllAmmunition() {
        List<Ammunition> ammunitionList = ammunitionService.findAll();
        if (ammunitionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ammunitionList, HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ammunition> saveAmmunition(@RequestBody @Valid Ammunition ammunition) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (ammunition == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ammunitionService.save(ammunition);

        return new ResponseEntity<>(ammunition, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ammunition> deleteAmmunition(@PathVariable("id") Long id) {
        Ammunition ammunition = ammunitionService.getDataById(id);
        if (ammunition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ammunitionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ammunition> updateAmmunition(
            @PathVariable("id") Long ammunitionId,
            @RequestBody @Valid Ammunition newAmmunition
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (newAmmunition == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ammunition ammunition = ammunitionService.getDataById(ammunitionId);
        if (ammunition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        ammunition.setId(newAmmunition.getId());
        ammunition.setName(newAmmunition.getName());
        ammunition.setDescription(newAmmunition.getDescription());
        ammunition.setCount(newAmmunition.getCount());
        ammunitionService.save(ammunition);
        return new ResponseEntity<>(ammunition, httpHeaders, HttpStatus.OK);
    }
}
