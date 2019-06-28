package cloudplaces.api;

import cloudplaces.webapp.CloudPlacesApplication;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudPlacesApplication.class)
@TestPropertySource (value={"classpath:application.properties"})
@AutoConfigureMockMvc
public class ApiTest {
        
    @Autowired
    private MockMvc mvc;
    
    
    private final static String baseUrl = "localhost:8080/api/";
    
    @Test
    public void verification() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/get_properties")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}
