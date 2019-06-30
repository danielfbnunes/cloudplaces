package pt.ua.cloudplacesandroidapp.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class User {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pw")
    @Expose
    private String pw;
    @SerializedName("cellphone")
    @Expose
    private String cellphone;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("rentals")
    @Expose
    private List<Object> rentals = null;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("wishes")
    @Expose
    private List<Object> wishes = null;
    @SerializedName("searches")
    @Expose
    private List<Object> searches = null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Object> getRentals() {
        return rentals;
    }

    public void setRentals(List<Object> rentals) {
        this.rentals = rentals;
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

}
