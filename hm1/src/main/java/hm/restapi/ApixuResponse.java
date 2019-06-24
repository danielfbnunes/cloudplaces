package hm.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApixuResponse {
    
    private Map<String, ArrayList<Map<String,Object>>> forecast;

    public Map<String, ArrayList<Map<String, Object>>> getForecast() {
        return forecast;
    }

    public void setForecast(Map<String, ArrayList<Map<String, Object>>> forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "ApixuResponse{" + "forecast=" + forecast + '}';
    }
    
    
}
