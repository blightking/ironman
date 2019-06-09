package guru.springframework.ironman.domain;

import javax.persistence.*;
import java.util.List;

//@Entity
public class Suit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "suit")
//    @JsonIgnore
    private List<AmmunitionSuit> ammunitionSuitList;

    public Suit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<AmmunitionSuit> getAmmunitionSuitList() {
        return ammunitionSuitList;
    }

    public void setAmmunitionSuitList(List<AmmunitionSuit> ammunitionSuitList) {
        this.ammunitionSuitList = ammunitionSuitList;
    }

    @Override
    public String toString() {
        return "Suit{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
