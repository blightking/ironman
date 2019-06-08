package guru.springframework.ironman.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ammo")
public class Ammunition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int count;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ammunition")
    @JsonIgnore
    private List<AmmunitionSuit> ammunitionSuitList;

    public Ammunition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AmmunitionSuit> getAmmunitionSuitList() {
        return ammunitionSuitList;
    }

    public void setAmmunitionSuitList(List<AmmunitionSuit> ammunitionSuitList) {
        this.ammunitionSuitList = ammunitionSuitList;
    }

    @Override
    public String toString() {
        return "AmmunitionService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", ammunitionSuitList=" + ammunitionSuitList +
                '}';
    }
}
