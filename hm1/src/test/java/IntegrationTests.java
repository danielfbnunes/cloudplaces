import hm.restapi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class IntegrationTests {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    public void verificationOfDarkSky() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/weather/darksky/40.6442700,-8.6455400,5")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    
    @Test
    public void verificationOfApixu() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/weather/apixu/40.6442700,-8.6455400,5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    
    @Test
    public void verificationOfCities() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/weather/cities").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }
    
    @Test
    public void verificationOfApis() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/weather/apis").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }
    
}
