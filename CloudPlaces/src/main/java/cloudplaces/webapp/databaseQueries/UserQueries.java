/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package cloudplaces.webapp.databaseQueries;

import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author DanielNunes
 */
public class UserQueries {
  
  @Autowired
  private PropertyRepository propertyRepo;
  
  @Autowired
  private UserRepository userRepo;
  
  @Autowired
  private EntityManager em;
  
  public boolean addUser(String name, String email, String pw, String cellphone, byte[] photo){
    System.out.println("HERE!!");
    User u = new User(name, email, pw, cellphone, photo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    if(em.createQuery("SELECT u FROM User u WHERE email = \'" + email + "\'"  ).getResultList().isEmpty()){
      userRepo.save(u);
      return true;
    }
    return false;
  }
  
  public User getUser(long user_id){
    return (User) em.createQuery("SELECT u FROM User u WHERE u.id = " + user_id).getResultList().get(0);
  }
  
  public ArrayList<Object> getWishlist(){
    return new ArrayList<>();
  }
  
  public boolean addToWishlist(){
    return true;
  }
  
  public boolean deleteFromWishlist(){
    return true;
  }
}
