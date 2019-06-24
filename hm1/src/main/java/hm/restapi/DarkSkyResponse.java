package hm.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyResponse{  
    
    private Map<String, ArrayList<WeatherInfo>> daily;

    public Map<String, ArrayList<WeatherInfo>> getDaily() {
        return daily;
    }

    public void setDaily(Map<String, ArrayList<WeatherInfo>> daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "{Prevision : " + daily.get("data").get(0)  +'}';
    }    
    
}
