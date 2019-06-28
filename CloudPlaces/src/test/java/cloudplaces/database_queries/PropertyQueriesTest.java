/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.database_queries;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.entities.House;
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
    
    // expected null because there no houses after reload test database
    List<House> expected = null;
    assertEquals(expected, houses);
  }
}
