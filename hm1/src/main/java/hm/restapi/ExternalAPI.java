package hm.restapi;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalAPI {
    RestTemplate rt;
    
    public ExternalAPI(){
        rt = new RestTemplate();
    }
    
    public DarkSkyResponse getDarkSky(double latitude, double longitude, long today, int day){
        return rt.getForObject("https://api.darksky.net/forecast/39e0920039b437dbb6c6bcddc0c06909/"+latitude+","+longitude+","+ (today + 86400*day )+ "?exclude=currently,flags,hourly,minutely", DarkSkyResponse.class);
    }
    
    public ApixuResponse getApixu(double latitude, double longitude, int nDays){
        return rt.getForObject("http://api.apixu.com/v1/forecast.json?key=3739194054cc4a4a95a112421190505&q="+ latitude+","+longitude+"&days="+nDays, ApixuResponse.class);
    }
}

