package pt.ua.cloudplacesandroidapp.API;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class House {

    @SerializedName("houseId")
    @Expose
    private Integer houseId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("habSpace")
    @Expose
    private Integer habSpace;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("publishDay")
    @Expose
    private String publishDay;
    @SerializedName("garage")
    @Expose
    private Integer garage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("propertyFeatures")
    @Expose
    private String propertyFeatures;
    @SerializedName("availability")
    @Expose
    private Integer availability;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("wishes")
    @Expose
    private List<Object> wishes = null;
    @SerializedName("searches")
    @Expose
    private List<Object> searches = null;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("nrooms")
    @Expose
    private Integer nrooms;
    @SerializedName("nbathrooms")
    @Expose
    private Integer nbathrooms;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHabSpace() {
        return habSpace;
    }

    public void setHabSpace(Integer habSpace) {
        this.habSpace = habSpace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Integer getGarage() {
        return garage;
    }

    public void setGarage(Integer garage) {
        this.garage = garage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyFeatures() {
        return propertyFeatures;
    }

    public void setPropertyFeatures(String propertyFeatures) {
        this.propertyFeatures = propertyFeatures;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public List<Object> getWishes() {
        return wishes;
    }

    public void setWishes(List<Object> wishes) {
        this.wishes = wishes;
    }

    public List<Object> getSearches() {
        return searches;
    }

    public void setSearches(List<Object> searches) {
        this.searches = searches;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Integer getNrooms() {
        return nrooms;
    }

    public void setNrooms(Integer nrooms) {
        this.nrooms = nrooms;
    }

    public Integer getNbathrooms() {
        return nbathrooms;
    }

    public void setNbathrooms(Integer nbathrooms) {
        this.nbathrooms = nbathrooms;
    }

}
