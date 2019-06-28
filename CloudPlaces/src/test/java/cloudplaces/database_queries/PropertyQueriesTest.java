/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.database_queries;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import cloudplaces.webapp.entities.House;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author DanielNunes
 */
@RunWith(SpringRunner.class)
@TestPropertySource (value={"classpath:application.properties"})
@SpringBootTest(classes = CloudPlacesApplication.class)
public class PropertyQueriesTest {
  
  @Autowired
  UserQueries userQueries;
  
  @Autowired
  PropertyQueries propertyQueries;
    
  @Autowired
  GeneralQueries generalQueries;
  
  public PropertyQueriesTest() {
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
    userQueries.addUser("Joao", "joao@ua.pt", "password", "987654321", "photo");
    propertyQueries.addProperty("House 1", "Aveiro", 100, 2, "joao@ua.pt", 50, 1, 2, "Nice house", "garden;garage", 1/*, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()*/);
  }
  
  @After
  public void tearDown() {
  }

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
