package cloudplaces.restapi.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class House {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long houseId;
    private String address;
    private int n_rooms;
    private int hab_space;
    private double price;
    private String name;
    private String publishDay;

    public House() {
    }
    
    public House(String address, int n_rooms, int hab_space, double price, String name, String date) {
        this.address = address;
        this.n_rooms = n_rooms;
        this.hab_space = hab_space;
        this.price = price;
        this.name = name;
        this.publishDay = date;
    }
    
}
