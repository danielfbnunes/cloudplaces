package cloudplaces.database_queries;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.Wishlist;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@TestPropertySource (value={"classpath:application.properties"})
@SpringBootTest(classes = CloudPlacesApplication.class)
public class QueriesTests {
  @Autowired
  UserQueries userQueries;

  @Autowired
  PropertyQueries propertyQueries;

  @Autowired
  GeneralQueries generalQueries;

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
      generalQueries.reloadTestDatabase();
      userQueries.addUser("Joao", "joao@ua.pt", "password", "987654321", "photo");
      propertyQueries.addProperty("House 1", "Aveiro", 100, 2, "joao@ua.pt", 50, 1, 2, "Nice house", "garden;garage", 1, new ArrayList<>());

  }

  @After
  public void tearDown() {
  }
  //User Queries Test

  /**
   * Test of addUser method, of class UserQueries.
   */
  @Test
  public void testAddUser() {
    System.out.println("addUser");
    String name = "name";
    String email = "email";
    String pw = "pw";
    String cellphone = "cellphone";
    String photo = "photo";

    User u = new User(name, email, pw, cellphone, photo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    User addedUser = userQueries.addUser(name, email, pw, cellphone, photo);

    assertEquals(u.getEmail(), addedUser.getEmail());

    // should be null, since the user is already in the database
    User addedUser2 = userQueries.addUser(name, email, pw, cellphone, photo);
    assertEquals(null, addedUser2);
  }

  /**
   * Test of authenticateUser method, of class UserQueries.
   */
  @Test
  public void testAuthenticateUser() {
    System.out.println("authenticateUser");

    String name = "name";
    String email = "email";
    String pw = "pw";
    String cellphone = "cellphone";
    String photo = "photo";

    User u = new User(name, email, pw, cellphone, photo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    User addedUser = userQueries.addUser(name, email, pw, cellphone, photo);
    // log user and check if it has success
    User loggedUserTestCorrect = userQueries.authenticateUser(email, pw);
    assertEquals(u.getEmail(), loggedUserTestCorrect.getEmail());

    // log user and check if it has success
    User loggedUserTestIncorrect = userQueries.authenticateUser(email, "aaaas");
    assertEquals(null, loggedUserTestIncorrect);
  }

  /**
   * Test of getUser method, of class UserQueries.
   */
  @Test
  public void testGetUserWhenIsNotAdded() {
    System.out.println("testGetUserWhenIsNotAdded");

    String email = "test@ua.pt";
    User expectedResult = null;

    // try to get inexistent user
    User result = userQueries.getUser(email);

    assertEquals(expectedResult, result);
  }
  
  /**
   * 
   */
  @Test
  public void testGetUserWhenIsAdded() {
    System.out.println("testGetUserWhenIsAdded");
    String email = "test@ua.pt";
    String name = "name";
    String pw = "pw";
    String cellphone = "cellphone";
    String photo = "photo";

    User newUser = userQueries.addUser(name, email, pw, cellphone, photo);

    assertEquals(newUser.getEmail(), email);
  }

  /**
   * Test of getWishlist method, of class UserQueries.
   */
  @Test
  @Ignore
  public void testGetWishlist() {
    System.out.println("getWishlist");

    ArrayList<Object> expResult = null;
    Object result = userQueries.getWishlist("test@ua.pt");
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of addToWishlist method, of class UserQueries.
   */
  @Test
  @Ignore
  public void testAddToWishlist() {
    System.out.println("addToWishlist");
    boolean expResult = false;
    Wishlist result = userQueries.addToWishlist("test@ua.pt", 0L);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteFromWishlist method, of class UserQueries.
   */
  @Test
  @Ignore
  public void testDeleteFromWishlist() {
    System.out.println("deleteFromWishlist");
    UserQueries instance = new UserQueries();
    boolean expResult = false;
    boolean result = instance.deleteFromWishlist("", 0);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  //PropertyQueries Test
  /**
   * Test of getAllProperties method, of class PropertyQueries.
   */
  @Test
  public void testGetAllProperties() {
    System.out.println("getAllProperties");
    List<House> houses = propertyQueries.getAllProperties();

    // expected 1 because there only one house (added on setUp method)
    int expectedSize = 1;
    assertEquals(expectedSize, houses.size());

    propertyQueries.removeProperty("House 1");
    houses = propertyQueries.getAllProperties();

    // expected 0, because shouldn't be any house.
    expectedSize = 0;
    assertEquals(expectedSize, houses.size());
  }

  /**
   * Test getProperties method, of class PropertyQueries.
   */
  @Test
  public void testGetPropertiesByNameWhenIsNotAdded() {
    System.out.println("testGetPropertiesByNameWhenIsNotAdded");
    //'House 2' shouldn't be found, so the expected value is null.
    List<House> expectedList = new ArrayList<>();
    List<House> houses = propertyQueries.getProperties("House 2", null, null, null, null, null, null, null, null);
    assertEquals(expectedList, houses);
  }
  
  /**
   * 
   */
  @Test
  public void testGetPropertiesByNameWhenIsAdded() {
    System.out.println("testGetPropertiesByNameWhenIsAdded");
    //'House 1' should be found, it was already added.
    String expectedName = "House 1";
    List<House> houses = propertyQueries.getProperties("House 1", null, null, null, null, null, null, null, null);
    assertEquals(expectedName, houses.get(0).getName());
  }

  /**
   * Test getProperties method, of class PropertyQueries.
   */
  @Test
  public void testGetPropertiesByPriceRangeWhenMinPriceIsSet() {
    System.out.println("testGetPropertiesByPriceRangeWhenMinPriceIsSet");
    Float expectedMinPrice = (float) 1;
    List<House> houses = propertyQueries.getProperties(null, null, (float) 1, null, null, null, null, null, null);
    assertThat(expectedMinPrice, lessThan(houses.get(0).getPrice()));
  }
  
  /**
   * 
   */
  @Test
  public void testGetPropertiesByPriceRangeWhenMaxPriceIsSet() {
    System.out.println("testGetPropertiesByPriceRangeWhenMaxPriceIsSet");
    Float expectedMaxPrice = (float) 150;
    List<House> houses = propertyQueries.getProperties(null, null, null, (float) 150, null, null, null, null, null);
    assertThat(expectedMaxPrice, greaterThan(houses.get(0).getPrice()));
  }

  /**
   * 
   */
  @Test
  public void testGetPropertiesByPriceRangeWhenMinAndMaxPricesAreSet() {
    System.out.println("testGetPropertiesByPriceRangeWhenMinAndMaxPricesAreSet");
    Float expectedMinPrice = (float) 1;
    Float expectedMaxPrice = (float) 150;
    List<House> houses = propertyQueries.getProperties(null, null, (float) 1, (float) 150, null, null, null, null, null);
    assertThat(expectedMinPrice, lessThan(houses.get(0).getPrice()));
    assertThat(expectedMaxPrice, greaterThan(houses.get(0).getPrice()));
  }

  /**
   * Test getProperties method, of class PropertyQueries.
   */
  @Test
  public void testGetPropertiesByHabSpaceWhenMinHabSpaceIsSet() {
    System.out.println("testGetPropertiesByHabSpaceWhenMinHabSpaceIsSet");
    Integer expectedMinHabSpace = 20;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, null, null, 20, null, null);
    assertThat(expectedMinHabSpace, lessThan(houses.get(0).getHabSpace()));
  }

  /**
   * 
   */
  public void testGetPropertiesByHabSpaceWhenMaxHabSpaceIsSet() {
    System.out.println("testGetPropertiesByHabSpaceWhenMaxHabSpaceIsSet");
    Integer expectedMaxHabSpace = 100;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, null, null, null, 100, null);
    assertThat(expectedMaxHabSpace, greaterThan(houses.get(0).getHabSpace()));
  }

  /**
   * 
   */
  public void testGetPropertiesByHabSpaceWhenMinAndMaxHabSpaceAreSet() {
    System.out.println("testGetPropertiesByHabSpaceWhenMinAndMaxHabSpaceAreSet");
    Integer expectedMinHabSpace = 20;
    Integer expectedMaxHabSpace = 100;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, null, null, 20, 100, null);
    assertThat(expectedMinHabSpace, lessThan(houses.get(0).getHabSpace()));
    assertThat(expectedMaxHabSpace, greaterThan(houses.get(0).getHabSpace()));
  }

  /**
   * Test getProperties method, of class PropertyQueries.
   */
  @Test
  public void testGetPropertiesByNRoomsWhenMinNRoomsIsSet() {
    System.out.println("getPropertiesByNRoomsWhenMinNRoomsIsSet");
    Integer expectedMinNRooms = 1;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, 1, null, null, null, null);
    assertThat(expectedMinNRooms, lessThan(houses.get(0).getNRooms()));
  }
  
  /**
   * 
   */
  @Test
  public void testGetPropertiesByNRoomsWhenMaxNRoomsIsSet() {
    System.out.println("getPropertiesByNRoomsWhenMaxNRoomsIsSet");
    Integer expectedMaxNRooms = 4;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, null, 4, null, null, null);
    assertThat(expectedMaxNRooms, greaterThan(houses.get(0).getNRooms()));
  }
  
  /**
   * 
   */
  @Test
  public void getPropertiesByNRoomsWhenMinAndMaxNRoomsAreSet() {
    System.out.println("getPropertiesByNRoomsWhenMinAndMaxNRoomsAreSet");
    Integer expectedMinNRooms = 1;
    Integer expectedMaxNRooms = 4;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, 1, 4, null, null, null);
    assertThat(expectedMinNRooms, lessThan(houses.get(0).getNRooms()));
    assertThat(expectedMaxNRooms, greaterThan(houses.get(0).getNRooms()));
  }

  /**
   * Test getProperties method, of class PropertyQueries.
   */
  @Test
  public void testGetPropertiesByAvailability() {
    System.out.println("getPropertiesByAvailability");
    int expectedAvailability = 1;
    List<House> houses = propertyQueries.getProperties(null, null, null, null, null, null, null, null, 1);
    assertEquals(expectedAvailability, houses.get(0).getAvailability());
  }

  /**
   * Test getProperty method, of class PropertyQueries.
   */
  @Test
  @Ignore
  public void testGetProperty(){
  }

  /**
   * Test editProperty method of PropertyQueries class.
   */
  @Test
  public void testEditProperty() {
    String expectedLocation = "Rua do deti, Aveiro, Portugal";

    House house = propertyQueries.editProperty("House 1", 
        "Rua do deti, Aveiro, Portugal", 
        100, 
        2, 
        "joao@ua.pt", 
        50, 
        1, 
        2, 
        "Nice house", 
        "garden;garage", 
        1, 
        new ArrayList<>());

    assertEquals(expectedLocation, house.getAddress());
  }

  /**
   * Test removeProperty method, of class PropertyQueries.
   */
  @Test
  @Ignore
  public void testRemoveProperty(){
  }
}
