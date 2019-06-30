package cloudplaces.api;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.containsString;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudPlacesApplication.class)
@TestPropertySource (value={"classpath:application.properties"})
@AutoConfigureMockMvc
public class ApiTest {
        
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private GeneralQueries generalQueries;
    
    @Autowired
    private PropertyQueries propertyQueries;
    
    @Autowired
    private PropertyRepository propertyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    private final ObjectMapper mapperObj = new ObjectMapper();
    
    private final User user = new User(
        "Daniel Nunes",
        "daniel@ua.pt",
        "password",
        "987654321",
        "",
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    
    private final House house = new House(
        "Rua 1",
        3,
        20,
        150,
        "House 1",
        "data",
        user,
        2,
        1,
        "Situated in Ladywell this room enables you to have both privacy and convince - being only 10 minutes away by train to central London. The short train ride to London Bridge,Waterloo and shortly stopping at Charing Cross which will enable you to roam around Covent Garden and cross the river to Southbank or even venture further by bus or the underground to Oxford Street, Regent Street and all other destinations. Whether you have a short or long stay in London - my place is perfect.",
        "library_garden_test1_test2_test3_test4",
        1,
        new ArrayList<>(),
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    
    @Before
    public void setUp() {
      userRepo.save(user);
      propertyRepo.save(house);
    }
    
    //PropertyResources Test
    /**
     * Test of api call api/get_properties, of class PropertyResources.
     */
    @Test
    public void getPropertiesTest() {
      try {
        mvc.perform(MockMvcRequestBuilders.get("/api/get_properties", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(content().string(containsString("\"name\":\"House 1\"")));
      }
      catch (Exception e) {
        fail("Unable to get all the properties!");
      }
    }
    
    @Test
    /**
     * Test of api call api/get_property?id={id}, of class PropertyResources.
     */
    public void getPropertyByIdTest() {
      try {
        mvc.perform(MockMvcRequestBuilders.get("/api/get_property?id=1", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"name\":\"House 1\"")));
      }
      catch (Exception e) {
        fail("Unable to find the property by its id!");
      }
    }
    
    @Test
    /**
     * Test of api call api/add_property/, of class PropertyResources.
     */
    public void addPropertyTest() {
      try {
        String houseJsonString = mapperObj.writeValueAsString(house);
        
        mvc.perform(MockMvcRequestBuilders.post("/api/add_property", 1L)
            .content(houseJsonString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"name\":\"House 1\"")));
      }
      catch (Exception e) {
        fail("Unable to add the property!");
      }
    }
    
    @Test
    /**
     * Test of api call api/edit_property/, of class PropertyResources.
     */
    public void editPropertyTest() {
      house.setAddress("Aveiro, Portugal");
      propertyRepo.save(house);
      
      try {
        String houseJsonString = mapperObj.writeValueAsString(house);
        
        mvc.perform(MockMvcRequestBuilders.put("/api/edit_property", 1L)
            .content(houseJsonString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"address\":\"Aveiro, Portugal\"")));
      }
      catch (Exception e) {
        fail("Unable to edit the property!");
      }
    }
    
    @Test
    @Ignore
    /**
     * Test of api call api/delete_property?name={name}, of class PropertyResources.
     */
    public void deletePropertyTest(){
      house.setName("House 2");
      propertyRepo.save(house);
      
      try {
        mvc.perform(MockMvcRequestBuilders.delete("/api/delete_property?name='House 2'", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"name\":\"House 2\"")));
      }
      catch (Exception e) {
        fail("Unable to remove the property!");
      }
    }
    
    //UserResources Test
    
    @Test
     /**
      * Test of api call api/authenticate/, of class UserResources.
     */
    public void authenticateUserTest(){
      try {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "daniel@ua.pt");
        credentials.put("password", "password");
        String credetialsString = mapperObj.writeValueAsString(credentials);
        mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
            .content(credetialsString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"email\":\"daniel@ua.pt\"")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    @Test
    /**
     * Test of api call api/add_user/, of class UserResources.
     */
    public void addUserTest() {
      user.setEmail("dani@ua.pt");
      
      try {
        String userJsonString = mapperObj.writeValueAsString(user);
        
        mvc.perform(MockMvcRequestBuilders.post("/api/add_user")
            .content(userJsonString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"email\":\"dani@ua.pt\"")));
      }
      catch (Exception e) {
        fail();
      }
      
    }
    
    @Test
    /**
     * Test error of api call api/add_user/, of class UserResources.
     */
    public void addUserErrorTest() {
      user.setEmail("daniel@ua.pt");
      
      try {
        String userJsonString = mapperObj.writeValueAsString(user);
        
        mvc.perform(MockMvcRequestBuilders.post("/api/add_user")
            .content(userJsonString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"Error\":\"User with the given email already in database\"")));
      }
      catch (Exception e) {
        fail();
      }
      
    }
    
    @Test
    /**
     * Test of api call api/get_user/{email}, of class UserResources.
     */
    public void getUserTest(){
      try {
        mvc.perform(MockMvcRequestBuilders.get("/api/get_user?email=daniel@ua.pt", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"name\":\"Daniel Nunes\"")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    @Test
    /**
     * Test error of api call api/get_user/{email}, of class UserResources.
     */
    public void getUserErrorTest(){
      try {
        mvc.perform(MockMvcRequestBuilders.get("/api/get_user?email=dan@ua.pt", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"Error\":\"User not found\"")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    @Test
    /**
     * Test of api call api/get_wishlsit/{user_email}, of class UserResources.
     */
    public void getWishListTest(){
      try {
        mvc.perform(MockMvcRequestBuilders.get("/api/get_wishlist?user_email=daniel@ua.pt", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("[]")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    @Test
    /**
     * Test of api call api/get_wishlsit/{user_email}, of class UserResources.
     */
    public void getWishListErrorTest(){
      try {
        mvc.perform(MockMvcRequestBuilders.get("/api/get_wishlist?user_email=dani@ua.pt", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"Error\":\"User not found with wishlist\"")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    @Test
    /**
     * Test of api call api/add_to_wishlsit/{user_email}, of class UserResources.
     */
    public void addToWishListTest(){
      try {
        Map<String, String> data = new HashMap<>();
        data.put("user_email", "daniel@ua.pt");
        data.put("property_id", "1");
        String dataString = mapperObj.writeValueAsString(data);
        mvc.perform(MockMvcRequestBuilders.post("/api/add_to_wishlist")
            .content(dataString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"whishListId\"")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    @Test
    /**
     * Test of api call api/add_to_wishlsit/{user_email}, of class UserResources.
     */
    public void addToWishListErrorTest(){
      try {
        Map<String, String> data = new HashMap<>();
        data.put("user_email", "dan@ua.pt");
        data.put("property_id", "1");
        String dataString = mapperObj.writeValueAsString(data);
        mvc.perform(MockMvcRequestBuilders.post("/api/add_to_wishlist")
            .content(dataString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"Error\":\"House or User in question not found\"")));
      }
      catch (Exception e) {
        fail();
      }
    }
    
    
    @Test
    @Ignore
    /**
     * Test of api call api/delete_from_wishlsit/{user_email}, of class UserResources.
     */
    public void deleteFromWishListTest(){
        fail("Query mal implementada");
    }
}
