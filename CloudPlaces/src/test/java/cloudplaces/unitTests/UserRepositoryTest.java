package cloudplaces.unitTests;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.db.House;
import cloudplaces.webapp.db.User;
import cloudplaces.webapp.db.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudPlacesApplication.class)
public class UserRepositoryTest {
    
    @Autowired
    private EntityManager em;
    
    @Autowired
    private UserRepository userRepo;
    
    @Test
    public void canItFindHousesByUserID() {
        House house = new House();
        house.setAddress("rua da casa");
        List<House> houses = new ArrayList<>();
        houses.add(house);
        User user = new User();
        user.setRentals(houses);
        userRepo.save(user);
        
        List users = userRepo.findAll();
        
        List<House> e = em.createQuery("SELECT h FROM House h").getResultList();;
        
        assertEquals(1, e.size());
    }
    
}