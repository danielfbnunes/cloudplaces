/**
 * Projeto Open source
 */

package cloudplaces.webapp.db;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * É um qualquer utilizador da aplicação.
 * 
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String pw;
    private int cellphone;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = House.class)
    private List<House> rentals;
    
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Review> reviews;
    
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Wishlist> wishes;
    
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<RecentSearches> searches;
    
    public User(){
    }

    public User(String name, String email, String pw, int cellphone, List<House> rentals, List<Review> reviews, List<Wishlist> wishes, List<RecentSearches> searches) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.cellphone = cellphone;
        this.rentals = rentals;
        this.reviews = reviews;
        this.wishes = wishes;
        this.searches = searches;
    }

    public Long getId() {
        return id;
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

    public int getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
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
}
