package cloudplaces.webapp.database_queries;

import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.HousePhotos;
import cloudplaces.webapp.entities.HousePhotosRepository;
import cloudplaces.webapp.entities.PropertyRepository;
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
  private HousePhotosRepository housePhotosRepo;

  @Autowired
  private EntityManager em;

  public List<House> getAllProperties(){
    String query = "SELECT h FROM House h";
    List<House> houseList = em.createQuery(query).getResultList();
    if (!houseList.isEmpty()) {
      return houseList;
    }
    return new ArrayList<>();
  }
  
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
      baseQuery += " AND h.habSpace >= " + minHabSpace + " AND h.habSpace <= " + maxHabSpace;
    }
    if (minHabSpace != null && maxHabSpace == null) {
      baseQuery += " AND h.habSpace >= " + minHabSpace;
    }
    if (minHabSpace == null && maxHabSpace != null) {
      baseQuery += " AND h.habSpace <= " + maxHabSpace;
    }

    if (minNRooms != null && maxNRooms != null) {
      baseQuery += " AND h.nRooms >= " + minNRooms + " AND h.nRooms <= " + maxNRooms;
    }
    if (minNRooms != null && maxNRooms == null) {
      baseQuery += " AND h.nRooms >= " + minNRooms;
    }
    if (minNRooms == null && maxNRooms != null) {
      baseQuery += " AND h.nRooms <= " + maxNRooms;
    }

    if (availability != null) {
      baseQuery += " AND h.availability = " + availability;
    }

    baseQuery += " )";
    List<House> houseList = em.createQuery(baseQuery).getResultList();
    
    if (!houseList.isEmpty()) {
      return houseList;
    }
    
    return new ArrayList<>();
  }

  public House getProperty(long id) {
    List<House> houseList = em.createQuery("SELECT h FROM House h WHERE h.houseId = " + id).getResultList();
    if (!houseList.isEmpty()) {
      return houseList.get(0);
    }
    return null;
  }

  public House addProperty(String name, String location, float price, int nRooms, String userEmail, int habSpace, int nBathrooms, int garage, String description, String propertyFeatures, int availability, List<HousePhotos> photos) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Optional<User> user = userRepo.findById(userEmail);
    
    if (user.isPresent()) {
      House h = new House(location, nRooms, habSpace, price, name, formatter.format(date), user.get(), nBathrooms, garage, description, propertyFeatures, availability, photos, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
      propertyRepo.save(h);
      return h;
    }
    
    return null;
  }

  public House editProperty(String name, String location, float price, int nRooms, String email, int habSpace, int nBathrooms, int garage, String description, String propertyFeatures, int availability, List<HousePhotos> photos) {
    Optional<User> user = userRepo.findById(email);
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    if (user.isPresent()) {
      String houseQuery = new StringBuilder("UPDATE House h SET h.address = '").append(location)
          .append("', h.availability = ").append(availability)
          .append(", h.description = '").append(description)
          .append("', h.garage = ").append(garage)
          .append(", h.habSpace = ").append(habSpace)
          .append(", h.nBathrooms = ").append(nBathrooms)
          .append(", h.nRooms = ").append(nRooms)
          .append(", h.price = ").append(price)
          .append(", h.propertyFeatures = '").append(propertyFeatures)
          .append("', h.publishDay = '").append(formatter.format(date))
          .append("' WHERE h.name = '").append(name).append("'")
          .toString();
      
      em.createQuery(houseQuery).executeUpdate();
      Optional<House> houseList = propertyRepo.findByName(name);
      
      if (houseList.isPresent()) {
        House house = houseList.get();
        
        if (!photos.isEmpty()) {
          housePhotosRepo.deleteAll(house.getPhotos());
          housePhotosRepo.saveAll(photos);
        }
        
        return house;
      }
    }
    
    return null;
  }

  public Integer removeProperty(String name) {
    Optional<House> house = propertyRepo.findByName(name);
    
    if (house.isPresent()){
      return propertyRepo.deleteByName(name);
    }
    
    return null;
  }
}
