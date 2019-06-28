/**
 * Projeto Open source
 */

package cloudplaces.webapp.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 * É um qualquer utilizador da aplicação.
 * 
 */
@Entity
public class User {
    
    @Id
    @Column(length = 128)
    private String email;
    private String name;
    private String pw;
    private String cellphone;
    @Lob
    private String photo;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = House.class)
    private List<House> rentals;
    
    @OneToMany( cascade = CascadeType.ALL, targetEntity = Review.class)
    @JoinColumn(name = "user_email")
    private List<Review> reviews;
    
    @OneToMany( cascade = CascadeType.ALL, targetEntity = Wishlist.class)
    @JoinColumn(name = "user_email")
    private List<Wishlist> wishes;
    
    @OneToMany( cascade = CascadeType.ALL, targetEntity = RecentSearches.class)
    @JoinColumn(name = "user_email")
    private List<RecentSearches> searches;
    
    public User(){
    }

    public User(String name, String email, String pw, String cellphone, String photo, List<House> rentals, List<Review> reviews, List<Wishlist> wishes, List<RecentSearches> searches) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.cellphone = cellphone;
        this.rentals = rentals;
        this.reviews = reviews;
        this.wishes = wishes;
        this.searches = searches;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<House> getRentals() {
        return rentals;
    }

    public void setRentals(List<House> rentals) {
        this.rentals = rentals;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
}
