/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.database_queries;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rd
 */
@RunWith(SpringRunner.class)
@TestPropertySource (value={"classpath:application.properties"})
@SpringBootTest(classes = CloudPlacesApplication.class)
public class UserQueriesTest {
  
  @Autowired
  UserQueries instance;
  
  @Autowired
  GeneralQueries generalQueries;
  
  public UserQueriesTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
      generalQueries.reloadTestDatabase();
  }
  
  @After
  public void tearDown() {
  }

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
    
    User u =  new User(name, email, pw, cellphone, photo, new ArrayList<House>() , new ArrayList<Review>() , new ArrayList<Wishlist>(), new ArrayList<RecentSearches>() );
    User addedUser = instance.addUser(name, email, pw, cellphone, photo);
    
    assertEquals(u.getEmail(), addedUser.getEmail());
    
    // should be null, since the user is already in the database
    User addedUser2 = instance.addUser(name, email, pw, cellphone, photo);
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
    
    User u =  new User(name, email, pw, cellphone, photo, new ArrayList<House>() , new ArrayList<Review>() , new ArrayList<Wishlist>(), new ArrayList<RecentSearches>() );
    User addedUser = instance.addUser(name, email, pw, cellphone, photo);
    // log user and check if it has success
    User loggedUserTestCorrect = instance.authenticateUser(email, pw);
    assertEquals(u.getEmail(), loggedUserTestCorrect.getEmail());
    
    
    // log user and check if it has success
    User loggedUserTestIncorrect = instance.authenticateUser(email, "aaaas");
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
    User result = instance.getUser(email);
    
    assertEquals(expectedResult, result);

    String name = "name";
    String pw = "pw";
    String cellphone = "cellphone";
    String photo = "photo";
    
    User newUser = instance.addUser(name, email, pw, cellphone, photo);

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
    List<House> result = instance.getWishlist("test@ua.pt");
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
    UserQueries instance = new UserQueries();
    boolean expResult = false;
    Wishlist result = instance.addToWishlist("test@ua.pt", 0L);
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
  
}
