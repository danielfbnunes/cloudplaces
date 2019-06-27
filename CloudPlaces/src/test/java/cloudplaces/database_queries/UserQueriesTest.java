/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.database_queries;

import cloudplaces.webapp.CloudPlacesApplication;
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
    byte[] photo = "photo".getBytes();
    
    User u =  new User(name, email, pw, cellphone, photo, new ArrayList<House>() , new ArrayList<Review>() , new ArrayList<Wishlist>(), new ArrayList<RecentSearches>() );
    User addedUser = instance.addUser(name, email, pw, cellphone, photo);
    
    assertEquals(u.getEmail(), addedUser.getEmail());
    
    // should be null, since the user is already in the database
    User addedUser2 = instance.addUser(name, email, pw, cellphone, photo);
    assertEquals(null, addedUser2);
  }

  /**
   * Test of getUser method, of class UserQueries.
   */
  @Test
  @Ignore
  public void testGetUser() {
    System.out.println("getUser");
    long user_id = 0L;
    UserQueries instance = new UserQueries();
    User expResult = null;
    User result = instance.getUser(user_id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getWishlist method, of class UserQueries.
   */
  @Test
  @Ignore
  public void testGetWishlist() {
    System.out.println("getWishlist");
    UserQueries instance = new UserQueries();
    ArrayList<Object> expResult = null;
    List<Object> result = instance.getWishlist();
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
    boolean result = instance.addToWishlist();
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
