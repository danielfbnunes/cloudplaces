package cloudplaces.restapi.db;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Review {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long reviewId;
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="Review_House", joinColumns=@JoinColumn(name = "reviewId"), inverseJoinColumns=@JoinColumn(name="houseId"))
    private List<House> houses;
    
    @JoinColumn
    @OneToOne
    private User lessee;

    public Review() {
    }

    public Review(List<House> houses, User lessee) {
        this.houses = houses;
        this.lessee = lessee;
    }
}
