/**
 * Projeto Open source
 */

package cloudplaces.webapp;

import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
    
    @Bean
    public PropertyQueries getPropertyQuery(){
        return new PropertyQueries();
    }
    
    @Bean
    public UserQueries getUserQuery(){
        return new UserQueries();
    }
    
    @Bean
    public GeneralQueries getGeneralQueries(){
        return new GeneralQueries();
    }
}

