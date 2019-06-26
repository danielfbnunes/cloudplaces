/**
 * Projeto Open source
 */

package cloudplaces.webapp;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
/**
 * Esta classe é utilizada para criar todo o contexto de execução da aplicação.
 */
public class CloudPlacesApplication{
    
    public static final Logger logger = LoggerFactory.getLogger(CloudPlacesApplication.class);
    public static final RestTemplate restTemplate = new RestTemplate();
    
    public static void main(String[] args){
        SpringApplication app = new SpringApplication(CloudPlacesApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        app.run(args);

    }
    
}

