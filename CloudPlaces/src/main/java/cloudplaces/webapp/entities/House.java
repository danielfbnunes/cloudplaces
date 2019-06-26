/**
 * Projeto Open source
 */
package cloudplaces.webapp.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * House é a classe que representa a entidade casa na base de dados.
 * 
 */
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long houseId;
    private String address;
    private int n_rooms;
    private int hab_space;
    private float price;
    private String name;
    private String publishDay;
    private int n_bathrooms;
    private int garage;
    private String description;
    private String property_features;
    private int availability;
    
    @ManyToOne
    private User user;
    
    @OneToMany
    @JoinColumn(name = "house_id")
    private List<Review> reviews;
    
    @OneToMany
    @JoinColumn(name = "house_id")
    private List<Wishlist> wishes;
    
    @OneToMany
    @JoinColumn(name = "house_id")
    private List<RecentSearches> searches;
    
    @OneToMany
    @JoinColumn(name = "house_id")
    private List<HousePhotos> photos;
    
    
    public House() {
    }

    public House(String address, int n_rooms, int hab_space, float price, String name, String publishDay, User user, int n_bathrooms, int garage, String description, String property_features, int availability, List<HousePhotos> photos, List<Review> reviews, List<Wishlist> wishes, List<RecentSearches> searches) {
        this.address = address;
        this.n_rooms = n_rooms;
        this.hab_space = hab_space;
        this.price = price;
        this.name = name;
        this.publishDay = publishDay;
        this.user = user;
        this.reviews = reviews;
        this.wishes = wishes;
        this.searches = searches;
        this.n_bathrooms = n_bathrooms;
        this.garage = garage;
        this.description = description;
        this.property_features = property_features;
        this.photos = photos;
        this.availability = availability;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Long getHouseId() {
        return houseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getN_rooms() {
        return n_rooms;
    }

    public void setN_rooms(int n_rooms) {
        this.n_rooms = n_rooms;
    }

    public int getHab_space() {
        return hab_space;
    }

    public void setHab_space(int hab_space) {
        this.hab_space = hab_space;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishDay() {
        return publishDay;
    }

    public void setPublishDay(String publishDay) {
        this.publishDay = publishDay;
    }
    
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Wishlist> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wishlist> wishes) {
        this.wishes = wishes;
    }

    public List<RecentSearches> getSearches() {
        return searches;
    }

    public void setSearches(List<RecentSearches> searches) {
        this.searches = searches;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGarage() {
        return garage;
    }

    public void setGarage(int garage) {
        this.garage = garage;
    }

    public int getN_bathrooms() {
        return n_bathrooms;
    }

    public void setN_bathrooms(int n_bathrooms) {
        this.n_bathrooms = n_bathrooms;
    }

    public List<HousePhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<HousePhotos> photos) {
        this.photos = photos;
    }

    public String getProperty_features() {
        return property_features;
    }

    public void setProperty_features(String property_features) {
        this.property_features = property_features;
    }
  
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
}
