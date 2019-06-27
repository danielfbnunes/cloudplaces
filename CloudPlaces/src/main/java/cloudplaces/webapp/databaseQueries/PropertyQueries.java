/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.webapp.databaseQueries;

import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.UserRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author DanielNunes
 */
public class PropertyQueries {
    
    @Autowired
    private PropertyRepository propertyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private EntityManager em;
    
    public List<House> getProperties(String name, String location, Float min_price, Float max_price, Integer min_n_rooms, Integer max_n_rooms, Integer min_hab_space, Integer max_hab_space, Integer availability){
        String baseQuery = "SELECT h FROM House h WHERE ( 1=1";
        if (name != null)
            baseQuery += " AND h.name LIKE '%" + name + "%'";
        if (location != null)
            baseQuery += " AND h.address LIKE '%" + location + "%'";
        
        if (min_price != null && max_price != null)
            baseQuery += " AND h.price >= " + min_price + " AND h.price <= " + max_price;
        if (min_price != null && max_price == null)
            baseQuery += " AND h.price >= " + min_price;
        if (min_price == null && max_price != null)
            baseQuery += " AND h.price <= " + max_price;
        
        if (min_hab_space != null && max_hab_space != null)
            baseQuery += " AND h.hab_space >= " + min_hab_space + " AND h.hab_space <= " + max_hab_space;
        if (min_hab_space != null && max_hab_space == null)
            baseQuery += " AND h.hab_space >= " + min_hab_space;
        if (min_hab_space == null && max_hab_space != null)
            baseQuery += " AND h.hab_space <= " + max_hab_space;
        
        if (min_n_rooms != null && max_n_rooms != null)
            baseQuery += " AND h.n_rooms >= " + min_n_rooms + " AND h.n_rooms <= " + max_n_rooms;
        if (min_n_rooms != null && max_hab_space == null)
            baseQuery += " AND h.n_rooms >= " + min_n_rooms;
        if (min_n_rooms == null && max_hab_space != null)
            baseQuery += " AND h.n_rooms <= " + max_n_rooms;
        
        if (availability != null)
            baseQuery += " AND h.availability <= " + availability;

        baseQuery += " )";
        System.out.println(baseQuery);
        return em.createQuery(baseQuery).getResultList();
    }
    
    public House getProperty(long id){
        return (House) em.createQuery("SELECT h FROM House h WHERE h.houseId = " + id).getResultList().get(0);
    }
    
    public House addProperty(String name, String location, float price, int n_rooms, long user_id, int hab_space, int n_bathrooms, int garage, String description, String property_features, int availability){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        House h = new House(location, n_rooms, hab_space, price, name, formatter.format(date), userRepo.findById(user_id).get(), n_bathrooms, garage, description, property_features, availability, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        propertyRepo.save(h);
        return h;
    }
    
    public boolean editProperty(long id){
        return true;
    }
    
    public boolean addReview(long user_id, long property_id, String review, String cotation){
        return true;
    }
    
    public boolean editReview(long review_id){
        return true;
    }
    
    public boolean deleteReview(long review_id){
        return true;
    }
}
