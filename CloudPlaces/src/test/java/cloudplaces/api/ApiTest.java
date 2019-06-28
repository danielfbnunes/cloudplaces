package cloudplaces.api;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import java.util.ArrayList;
import org.junit.Before;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudPlacesApplication.class)
@TestPropertySource (value={"classpath:application.properties"})
@AutoConfigureMockMvc
public class ApiTest {
        
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private GeneralQueries generalQueries;
    
    @Autowired
    private PropertyQueries propertyQueries;
    
    
    @Autowired
    private PropertyRepository propertyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    private final static String baseUrl = "localhost:8080/api/";
    
    
    @Before
    public void cleanUp(){
        generalQueries.reloadTestDatabase();
    }
    
    @Test
    public void verification() throws Exception{
        
        String expectedResult = "[{" +
                "\"houseId\": 1," +
                "\"address\": \"Rua 1\"," +
                "\"habSpace\": 20," +
                "\"price\": 150," +
                "\"name\": \"House 1\"," +
                "\"publishDay\": \"data\"," +
                "\"garage\": 1," +
                "\"description\": \"Situated in Ladywell this room enables you to have both privacy and convince - being only 10 minutes away by train to central London. The short train ride to London Bridge,Waterloo and shortly stopping at Charing Cross which will enable you to roam around Covent Garden and cross the river to Southbank or even venture further by bus or the underground to Oxford Street, Regent Street and all other destinations. Whether you have a short or long stay in London - my place is perfect.\"," +
                "\"propertyFeatures\": \"library_garden_test1_test2_test3_test4\"," +
                "\"availability\": 1," +
                "\"user\": {" +
                "\"email\": \"daniel@ua.pt\"," +
                "\"name\": \"Daniel Nunes\"," +
                "\"pw\": \"password\"," +
                "\"cellphone\": \"987654321\","+
                "\"photo\": \"\", "+
                "\"rentals\": []," +
                "\"reviews\": []," +
                "\"wishes\": []," +
                "\"searches\": []" +
                "},"+
                "\"reviews\": []," +
                "\"wishes\": []," +
                "\"searches\": []," +
                "\"photos\": [],"+
                "\"nrooms\": 3," +
                "\"nbathrooms\": 2 }]";
        User u1 = new User("Daniel Nunes",
            "daniel@ua.pt",
            "password",
            "987654321",
            "",
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userRepo.save(u1);
        
        House h1 = new House(
                "Rua 1",
                3,
                20,
                150,
                "House 1",
                "data",
                u1,
                2,
                1,
                "Situated in Ladywell this room enables you to have both privacy and convince - being only 10 minutes away by train to central London. The short train ride to London Bridge,Waterloo and shortly stopping at Charing Cross which will enable you to roam around Covent Garden and cross the river to Southbank or even venture further by bus or the underground to Oxford Street, Regent Street and all other destinations. Whether you have a short or long stay in London - my place is perfect.",
                "library_garden_test1_test2_test3_test4",
                1,
                new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
        );
        propertyRepo.save(h1);
        
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/get_properties")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }
}
