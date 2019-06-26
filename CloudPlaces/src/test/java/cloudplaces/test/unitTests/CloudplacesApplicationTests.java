package cloudplaces.test.unitTests;

import cloudplaces.webapp.CloudPlacesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudPlacesApplication.class)
public class CloudplacesApplicationTests {

	@Test
	public void contextLoads() {
            System.out.println("DEU");
	}

}
