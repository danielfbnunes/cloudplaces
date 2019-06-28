/**
 * Projeto Open source
 */
package cloudplaces.webapp.pojo;

import cloudplaces.webapp.entities.HousePhotos;
import cloudplaces.webapp.entities.RecentSearches;
import cloudplaces.webapp.entities.Review;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.Wishlist;
import java.util.List;

/**
 * House é a classe que representa a entidade casa na base de dados.
 * 
 */
public class HousePOJO {
    private Long houseId;
    private String address;
    private int nRooms;
    private int habSpace;
    private float price;
    private String name;
    private String publishDay;
    private int nBathrooms;
    private int garage;
    private String description;
    private String propertyFeatures;
    private int availability;
    
    private User user;

    private List<Review> reviews;

    private List<Wishlist> wishes;
    
    private List<RecentSearches> searches;
    
    private List<HousePhotos> photos;
    
    public HousePOJO() {
    }

    public HousePOJO(String address, int nRooms, int habSpace, float price, String name, String publishDay, User user, int nBathrooms, int garage, String description, String propertyFeatures, int availability, List<HousePhotos> photos, List<Review> reviews, List<Wishlist> wishes, List<RecentSearches> searches) {
        this.address = address;
        this.nRooms = nRooms;
        this.habSpace = habSpace;
        this.price = price;
        this.name = name;
        this.publishDay = publishDay;
        this.user = user;
        this.reviews = reviews;
        this.wishes = wishes;
        this.searches = searches;
        this.nBathrooms = nBathrooms;
        this.garage = garage;
        this.description = description;
        this.propertyFeatures = propertyFeatures;
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

    public int getNRooms() {
        return nRooms;
    }

    public void setNRooms(int nRooms) {
        this.nRooms = nRooms;
    }

    public int getHabSpace() {
        return habSpace;
    }

    public void setHabSpace(int habSpace) {
        this.habSpace = habSpace;
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

    public int getNBathrooms() {
        return nBathrooms;
    }

    public void setNBathrooms(int nBathrooms) {
        this.nBathrooms = nBathrooms;
    }

    public List<HousePhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<HousePhotos> photos) {
        this.photos = photos;
    }

    public String getPropertyFeatures() {
        return propertyFeatures;
    }

    public void setPropertyFeatures(String propertyFeatures) {
        this.propertyFeatures = propertyFeatures;
    }
  
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
}
