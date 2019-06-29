package cloudplaces.database_queries;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.RecentSearches;
import cloudplaces.webapp.entities.Review;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.Wishlist;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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

        User u = new User(name, email, pw, cellphone, photo, new ArrayList<House>(), new ArrayList<Review>(), new ArrayList<Wishlist>(), new ArrayList<RecentSearches>());
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

        User u = new User(name, email, pw, cellphone, photo, new ArrayList<House>(), new ArrayList<Review>(), new ArrayList<Wishlist>(), new ArrayList<RecentSearches>());
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
    public void testGetUser() {
        System.out.println("getUser");

        String email = "test@ua.pt";
        User expectedResult = null;

        // try to get inexistent user
        User result = userQueries.getUser(email);

        assertEquals(expectedResult, result);

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
        List<House> result = userQueries.getWishlist("test@ua.pt");
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
        boolean result = instance.deleteFromWishlist();
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
        List<House> houses = propertyQueries.getAllProperties();

        // expected 1 because there only one house (added on setUp method)
        int expected = 1;
        assertEquals(expected, houses.size());
    }

    /**
     * Test getProperties method, of class PropertyQueries.
     * Here, name parameter is the one that is tested.
     */
    @Test
    public void testGetPropertiesByName(){
        //'House 2' shouldn't be found, so the expected value is null.
        List<House> expectedList = new ArrayList<>();
        List<House> houses = propertyQueries.getProperties("House 2", null, null, null, null, null, null, null, null);
        assertEquals(expectedList, houses);

        //'House 1' should be found, it was already added.
        String expectedName = "House 1";
        houses = propertyQueries.getProperties("House", null, null, null, null, null, null, null, null);
        assertEquals(expectedName, houses.get(0).getName());
    }
}
