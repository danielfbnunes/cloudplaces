/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package cloudplaces.webapp.mappings;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import org.junit.Ignore;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
/**
 *
 * @author rd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudPlacesApplication.class)
@AutoConfigureMockMvc

public class CloudPlacesControllerTest {
  
  public CloudPlacesControllerTest() {
  }
  
  @Autowired
  private MockMvc mvc;
  
  
  @MockBean
  private PropertyQueries propertyQueries;
  
  
  
  
  Gson gson = new Gson();
  
  /**
   * Test of propertiesPagePost method, of class CloudPlacesController.
   * Condition 1 - name is null
   */
  @Test
  public void testPropertiesPagePostNullName(){
    System.out.println("testPropertiesPagePostNullName");
    // prepare output data
    List<House> output1= new ArrayList<>();
    
    when(propertyQueries.getProperties(null, "Aveiro",(float)2, (float) 3, 4, 5, null, null, null)).thenReturn(output1);
    
    try{
      this.mvc.perform(get("/propertiesSearch?"
              + "location=Aveiro"
              + "&min_price=2"
              + "&max_price=3"
              + "&min_rooms=4"
              + "&max_rooms=5"
              , 1L)).andExpect(status().isOk()).andExpect(content().string(containsString(gson.toJson(output1))));
    } catch (Exception ex) {
      fail("Unable to Convert Url");
    }
    
  }
  
  
  /**
   * Test of propertiesPagePost method, of class CloudPlacesController.
   * Condition 2 - location is null
   */
  @Test
  public void testPropertiesPagePostNullLocation(){
    System.out.println("testPropertiesPagePostNullLocation");
    // prepare output data
    List<House> output1= new ArrayList<>();
    
    House h1 = new House();
    h1.setHabSpace(33);
    output1.add(h1);
    
    when(propertyQueries.getProperties("Casa do Prado", null,(float)2, (float) 3, 4, 5, null, null, null)).thenReturn(output1);
    
    try{
      this.mvc.perform(get("/propertiesSearch?"
              + "name=Casa do Prado"
              + "&min_price=2"
              + "&max_price=3"
              + "&min_rooms=4"
              + "&max_rooms=5"
              , 1L)).andExpect(status().isOk()).andExpect(content().string(containsString(("\"habSpace\":33"))));
    } catch (Exception ex) {
      fail("Unable to Convert Url");
    }
    
  }
  
  
  /**
   * Test of propertiesPagePost method, of class CloudPlacesController.
   * Condition 3 - no one is null
   */
  @Test
  public void testPropertiesPagePostNoNulls(){
    System.out.println("testPropertiesPagePostNoNulls");
    // prepare output data
    List<House> output1= new ArrayList<>();
    
    House h1 = new House();
    h1.setHabSpace(100);
    output1.add(h1);
    when(propertyQueries.getProperties("Casa do Prado", "Porto",(float)2, (float) 3, 4, 5, null, null, null)).thenReturn(output1);
    
    try{
      this.mvc.perform(get("/propertiesSearch?"
              + "name=Casa do Prado"
              + "&location=Porto"
              + "&min_price=2"
              + "&max_price=3"
              + "&min_rooms=4"
              + "&max_rooms=5"
              , 1L)).andExpect(status().isOk()).andExpect(content().string(containsString(("\"habSpace\":100"))));
    } catch (Exception ex) {
      fail("Unable to Convert Url");
    }
  }
  
  
  /**
   * Test of postSignUp method, of class CloudPlacesController.
   */
  @Test
  @Ignore
  //Todo This test
  public void testPostSignUp() {
    System.out.println("postSignUp");
    Map<String, String> postPayload = null;
    CloudPlacesController instance = new CloudPlacesController();
    String expResult = "";
    String result = instance.postSignUp(postPayload);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
