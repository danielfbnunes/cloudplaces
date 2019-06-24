package hm.restapi;

import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class WeatherController {
    RestConsumer rc;
    
    public WeatherController(RestConsumer rc){
        this.rc = rc;
    }
    
    @GetMapping(value = "/weather/darksky/{latitude},{longitude},{nDays}", produces = {"application/json"})
    public Object getWeatherDarkSky(@PathVariable(value = "latitude") double latitude, @PathVariable(value = "longitude") double longitude, @PathVariable(value = "nDays") int nDays) {
        return rc.fetchInfoDarkSky(latitude, longitude, nDays, System.currentTimeMillis() / 1000L);
    }
    
    @GetMapping(value = "/weather/apixu/{latitude},{longitude},{nDays}", produces = {"application/json"})
    public Object getWeatherApixu(@PathVariable(value = "latitude") double latitude, @PathVariable(value = "longitude") double longitude, @PathVariable(value = "nDays") int nDays) {
        return rc.fetchInfoApixu(latitude, longitude, nDays);
    }
    
    @GetMapping(value = "/weather/cities")
    public Map<String, Object> getCities(){
        return rc.getAvailableCities();
    }
    
    @GetMapping(value = "/weather/apis")
    public Map<String, Object> getApis(){
        return rc.getAvailableApis();
    }
    
}
