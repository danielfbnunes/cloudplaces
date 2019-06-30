
package cloudplaces.webapp.database_queries;

import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import cloudplaces.webapp.entities.Wishlist;
import cloudplaces.webapp.entities.WishlistRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class UserQueries {
  
  @Autowired
  private PropertyRepository propertyRepo;
  
  @Autowired
  private UserRepository userRepo;
  
  @Autowired
  private WishlistRepository wishlistRepo;
  
  @Autowired
  private EntityManager em;
  
  
  public User addUser(String name, String email, String pw, String cellphone, String photo){
    User u = new User(name, email, pw, cellphone, photo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    if(em.createQuery("SELECT u FROM User u WHERE u.email = \'" + email + "\'"  ).getResultList().isEmpty()){
      userRepo.save(u);
      return u;
    }
    return null;
  }
  
  public User authenticateUser(String email, String pw){
    List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.email = \'" + email + "\' AND u.pw = \'" + pw + "\'" ).getResultList();
    if(!userList.isEmpty()){
      return userList.get(0);
    }
    return null;
  }
  
  public User getUser(String email){
    List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.email = '" + email + "'" ).getResultList();
    if(!userList.isEmpty()){
      return userList.get(0);
    }
    return null;
  }
  
  public Object getWishlist(String userEmail){
    Optional<User> user = userRepo.findById(userEmail);
    if (user.isPresent()){
      List<Wishlist> wishList = em.createQuery("select u.wishes from User u WHERE u.email = '" + userEmail + "'").getResultList();
      List<House> houses = new ArrayList<>();
      List<House> houseWishes = em.createQuery("select h from House h").getResultList();
      for (Wishlist w : wishList){
        for (House h : houseWishes){
          for (Wishlist w2 : h.getWishes()){
            if (Objects.equals(w.getWhishListId(), w2.getWhishListId())){
              houses.add(h);
            }
          }
        }
      }
      return houses;
    }
    return null;
  }
  
  public Wishlist addToWishlist(String userEmail, long propertyId){
    Optional<User> user = userRepo.findById(userEmail);
    Optional<House> house = propertyRepo.findById(propertyId);
    if (house.isPresent() && user.isPresent()){
      Wishlist w = new Wishlist();
      User u = user.get();
      List<Wishlist> wishlist = u.getWishes();
      wishlist.add(w);
      u.setWishes(wishlist);
      em.merge(u);
      w = (Wishlist) em.createQuery("SELECT w FROM Wishlist w ORDER BY w.whishListId DESC").setMaxResults(1).getResultList().get(0);
      House h = house.get();
      wishlist = h.getWishes();
      wishlist.add(w);
      h.setWishes(wishlist);
      em.merge(h);
      return w;
    }
    return null;
  }
  
  public boolean deleteFromWishlist(String email, long property_id) {
    Optional<User> user = userRepo.findById(email);
    Optional<House> house = propertyRepo.findById(property_id);
    if (user.isPresent() && house.isPresent()){
      User u = user.get();
      House h = house.get();
      List<Wishlist> userWishlist = u.getWishes();
      List<Wishlist> houseWishlist = h.getWishes();
      for (Wishlist w1 : userWishlist){
        for (Wishlist w2 : houseWishlist){
          if (w1.getWhishListId().equals(w2.getWhishListId())){
            Optional<Wishlist> wishlist = wishlistRepo.findById(w1.getWhishListId());
            if (wishlist.isPresent()){
              em.createQuery("DELETE FROM Wishlist w WHERE w.whishListId = " + w1.getWhishListId().toString()).executeUpdate();
              return true;
            }
          }
        }
      }
      return false;
    }
    return false;
  }
}
