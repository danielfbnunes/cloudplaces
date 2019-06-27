/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.webapp.databaseQueries;

import cloudplaces.webapp.entities.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author rd
 */
public class UserQueriesTest {
  
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
  @Ignore
  public void testAddUser() {
    System.out.println("addUser");
    String name = "";
    String email = "";
    String pw = "";
    String cellphone = "";
    byte[] photo = null;
    UserQueries instance = new UserQueries();
    boolean expResult = false;
    boolean result = instance.addUser(name, email, pw, cellphone, photo);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
    ArrayList<Object> result = instance.getWishlist();
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
