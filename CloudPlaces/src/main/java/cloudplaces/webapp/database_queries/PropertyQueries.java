/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.webapp.database_queries;

import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.UserRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class PropertyQueries {  
  @Autowired
  private PropertyRepository propertyRepo;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private EntityManager em;

  public List<House> getProperties(String name, String location, Float minPrice, Float maxPrice, Integer minNRooms, Integer maxNRooms, Integer minHabSpace, Integer maxHabSpace, Integer availability) {
    String baseQuery = "SELECT h FROM House h WHERE ( 1=1";
    if (name != null)
        baseQuery += " AND h.name LIKE '%" + name + "%'";
    if (location != null)
        baseQuery += " AND h.address LIKE '%" + location + "%'";

    if (minPrice != null && maxPrice != null)
        baseQuery += " AND h.price >= " + minPrice + " AND h.price <= " + maxPrice;
    if (minPrice != null && maxPrice == null)
        baseQuery += " AND h.price >= " + minPrice;
    if (minPrice == null && maxPrice != null)
        baseQuery += " AND h.price <= " + maxPrice;

    if (minHabSpace != null && maxHabSpace != null)
        baseQuery += " AND h.hab_space >= " + minHabSpace + " AND h.hab_space <= " + maxHabSpace;
    if (minHabSpace != null && maxHabSpace == null)
        baseQuery += " AND h.hab_space >= " + minHabSpace;
    if (minHabSpace == null && maxHabSpace != null)
        baseQuery += " AND h.hab_space <= " + maxHabSpace;

    if (minNRooms != null && maxNRooms != null)
        baseQuery += " AND h.n_rooms >= " + minNRooms + " AND h.n_rooms <= " + maxNRooms;
    if (minNRooms != null && maxHabSpace == null)
        baseQuery += " AND h.n_rooms >= " + minNRooms;
    if (minNRooms == null && maxHabSpace != null)
        baseQuery += " AND h.n_rooms <= " + maxNRooms;

    if (availability != null)
        baseQuery += " AND h.availability <= " + availability;

    baseQuery += " )";
    //System.out.println(baseQuery); TODO Converter para loggers
    return em.createQuery(baseQuery).getResultList();
  }

  public House getProperty(long id) {
    return (House) em.createQuery("SELECT h FROM House h WHERE h.houseId = " + id).getResultList().get(0);
  }

  public House addProperty(String name, String location, float price, int nRooms, long userId, int habSpace, int nBathrooms, int garage, String description, String propertyFeatures, int availability) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    House h = new House(location, nRooms, habSpace, price, name, formatter.format(date), userRepo.findById(userId).get(), nBathrooms, garage, description, propertyFeatures, availability, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    propertyRepo.save(h);
    return h;
  }

  public House editProperty(String name, String location, float price, int nRooms, long userId, int habSpace, int nBathrooms, int garage, String description, String propertyFeatures, int availability) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    House h = new House(location, nRooms, habSpace, price, name, formatter.format(date), userRepo.findById(userId).get(), nBathrooms, garage, description, propertyFeatures, availability, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    propertyRepo.save(h);
    return h;
  }
  
  public void removeProperty(long houseId) {
    propertyRepo.deleteById(houseId);
  }

  public boolean addReview(long userId, long propertyId, String review, String cotation) {
    return true;
  }

  public boolean editReview(long reviewId) {
    return true;
  }

  public boolean deleteReview(long reviewId) {
    return true;
  }
}
