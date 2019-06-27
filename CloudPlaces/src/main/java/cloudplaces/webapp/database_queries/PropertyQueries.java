/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.webapp.database_queries;

import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.Review;
import cloudplaces.webapp.entities.ReviewRepository;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class PropertyQueries {

  @Autowired
  private PropertyRepository propertyRepo;

  @Autowired
  private UserRepository userRepo;
  
  @Autowired
  private ReviewRepository reviewRepo;

  @Autowired
  private EntityManager em;

  public List<House> getProperties(String name, String location, Float minPrice, Float maxPrice, Integer minNRooms, Integer maxNRooms, Integer minHabSpace, Integer maxHabSpace, Integer availability) {
    String baseQuery = "SELECT h FROM House h WHERE ( 1=1";
    if (name != null) {
      baseQuery += " AND h.name LIKE '%" + name + "%'";
    }
    if (location != null) {
      baseQuery += " AND h.address LIKE '%" + location + "%'";
    }

    if (minPrice != null && maxPrice != null) {
      baseQuery += " AND h.price >= " + minPrice + " AND h.price <= " + maxPrice;
    }
    if (minPrice != null && maxPrice == null) {
      baseQuery += " AND h.price >= " + minPrice;
    }
    if (minPrice == null && maxPrice != null) {
      baseQuery += " AND h.price <= " + maxPrice;
    }

    if (minHabSpace != null && maxHabSpace != null) {
      baseQuery += " AND h.hab_space >= " + minHabSpace + " AND h.hab_space <= " + maxHabSpace;
    }
    if (minHabSpace != null && maxHabSpace == null) {
      baseQuery += " AND h.hab_space >= " + minHabSpace;
    }
    if (minHabSpace == null && maxHabSpace != null) {
      baseQuery += " AND h.hab_space <= " + maxHabSpace;
    }

    if (minNRooms != null && maxNRooms != null) {
      baseQuery += " AND h.n_rooms >= " + minNRooms + " AND h.n_rooms <= " + maxNRooms;
    }
    if (minNRooms != null && maxHabSpace == null) {
      baseQuery += " AND h.n_rooms >= " + minNRooms;
    }
    if (minNRooms == null && maxHabSpace != null) {
      baseQuery += " AND h.n_rooms <= " + maxNRooms;
    }

    if (availability != null) {
      baseQuery += " AND h.availability <= " + availability;
    }

    baseQuery += " )";
    List<House> houseList = em.createQuery(baseQuery).getResultList();
    if (!houseList.isEmpty()) {
      return houseList;
    }
    return null;
  }

  public House getProperty(long id) {
    List<House> houseList = em.createQuery("SELECT h FROM House h WHERE h.houseId = " + id).getResultList();
    if (!houseList.isEmpty()) {
      return houseList.get(0);
    }
    return null;
  }

  public House addProperty(String name, String location, float price, int nRooms, long userId, int habSpace, int nBathrooms, int garage, String description, String propertyFeatures, int availability) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Optional<User> user = userRepo.findById(userId);
    if (user.isPresent()) {
      House h = new House(location, nRooms, habSpace, price, name, formatter.format(date), user.get(), nBathrooms, garage, description, propertyFeatures, availability, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
      propertyRepo.save(h);
      return h;
    }
    return null;
  }

  public House editProperty(String name, String location, float price, int nRooms, long userId, int habSpace, int nBathrooms, int garage, String description, String propertyFeatures, int availability) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Optional<User> user = userRepo.findById(userId);
    if (user.isPresent()) {
      House h = new House(location, nRooms, habSpace, price, name, formatter.format(date), user.get(), nBathrooms, garage, description, propertyFeatures, availability, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
      propertyRepo.save(h);
      return h;
    }
    return null;
  }

  public boolean removeProperty(long houseId) {
    Optional<House> house = propertyRepo.findById(houseId);
    if (house.isPresent()){
      propertyRepo.deleteById(houseId);
      return true;
    }
    return false;
  }

  public Review addReview(long userId, long propertyId, String review, int quotation) {
    Optional<User> user = userRepo.findById(userId);
    Optional<House> house = propertyRepo.findById(propertyId);
    if (house.isPresent() && user.isPresent()){
      Review r = new Review(review, quotation);
      User u = user.get();
      List<Review> reviewList = u.getReviews();
      reviewList.add(r);
      u.setReviews(reviewList);
      em.merge(u);
      r = (Review) em.createQuery("SELECT r FROM Review r ORDER BY r.reviewId DESC").setMaxResults(1).getResultList().get(0);
      House h = house.get();
      reviewList = h.getReviews();
      reviewList.add(r);
      h.setReviews(reviewList);
      em.merge(h);
      return r;
    }
    return null;
  }

  public boolean editReview(long reviewId) {
    return true;
  }

  public boolean deleteReview(long reviewId) {
    Optional<Review> review = reviewRepo.findById(reviewId);
    if (review.isPresent()){
      reviewRepo.deleteById(reviewId);
      return true;
    }
    return false;
  }
}
