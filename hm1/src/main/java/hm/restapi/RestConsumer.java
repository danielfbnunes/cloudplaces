package hm.restapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RestConsumer {
    ExternalAPI api;      
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    int year = calendar.get(Calendar.YEAR);
    HashMap<String, SuccessMessage> darkskyCache;
    HashMap<String, SuccessMessage> apixuCache;
    
    public RestConsumer(ExternalAPI api){
        this.api = api;
        darkskyCache = new HashMap<>();
        apixuCache = new HashMap<>();
    }
    
    //Add Exception Throw
    public SuccessMessage fetchInfoDarkSky(double latitude, double longitude, int nDays, long today){
        if(darkskyCache.containsKey(""+latitude+longitude+nDays)){
            return darkskyCache.get(""+latitude+longitude+nDays);
        }
        else{
            int[] currentDay = {calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1};
            List<int[]> days = this.getDates(nDays, currentDay, year);
            DarkSkyResponse dk;
            ArrayList<WeatherInfo> response = new ArrayList();

            for(int day = 0; day < nDays; day ++){
                dk = api.getDarkSky(latitude, longitude, today, day);
                WeatherInfo temp = dk.getDaily().get("data").get(0);
                temp.setDay(days.get(day));
                response.add(temp);
            }
            SuccessMessage res =  new SuccessMessage(1, response);
            darkskyCache.put(""+latitude+longitude+nDays, res);
            return res;
        }
    }
    
    //Add Exception Throw
    public SuccessMessage fetchInfoApixu(double latitude, double longitude, int nDays){
        
        if(apixuCache.containsKey(""+latitude+longitude+nDays)){
            return apixuCache.get(""+latitude+longitude+nDays);
        }
        else{
            ArrayList<WeatherInfo> response = new ArrayList();
            int[] currentDay = {calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1};
            List<int[]> days = this.getDates(nDays, currentDay, year);

            ApixuResponse ar = api.getApixu(latitude, longitude, nDays);
            ArrayList<Map<String, Object>> previsions = ar.getForecast().get("forecastday");

            for(int dayInfo = 0; dayInfo < previsions.size(); dayInfo++){
                WeatherInfo wi = new WeatherInfo();
                Map<String, Object> day = (Map<String, Object>)previsions.get(dayInfo).get("day");
                wi.setTemperatureMax((double)day.get("maxtemp_f"));
                wi.setTemperatureMin((double)day.get("mintemp_f"));
                wi.setSummary(((Map<String,String>)day.get("condition")).get("text"));
                wi.setDay(days.get(dayInfo));
                response.add(wi);
            }
            SuccessMessage res = new SuccessMessage(1, response);
            apixuCache.put(""+latitude+longitude+nDays, res);
            return res;
        }
    }
    
    public Map<String, Object>  getAvailableCities(){
        Map<String, double[]> temp = new HashMap<>();
        double[][] tempArray = {{40.6442700,-8.6455400}, {41.1496100,-8.6109900},{38.7166700,-9.1333300},{40.2056400,-8.4195500},{40.6610100,-7.9097100} };
        temp.put("Aveiro", tempArray[0]);
        temp.put("Porto", tempArray[1]);
        temp.put("Lisboa", tempArray[2]);
        temp.put("Coimbra", tempArray[3]);
        temp.put("Viseu", tempArray[4]);
        Map<String, Object> response = new HashMap<>();
        response.put("id", 2);
        response.put("data", temp);
        return response;
    }
    
    public Map<String, Object> getAvailableApis(){
        Map<String, Object> response = new HashMap<>();
        String[] apis = {"darksky", "apixu"};
        response.put("data", apis);
        response.put("id", 3);
        return response;
    }
    
    public List<int[]> getDates(int nDays, int[] currentDay, int year){
        int[] months31 = {0,1,3,5,7,8,10,12};
        int[] months30 = {0,4,6,9,11};
        
        ArrayList<int[]> selectedDays = new ArrayList<>();
        
        if(Arrays.binarySearch(months31, currentDay[1]) > 0){
            for(int i = 0; i < nDays; i++){
                int[] newDay = {currentDay[0], currentDay[1]};
                if(currentDay[0] <= 31){                         
                    selectedDays.add(newDay);
                    currentDay[0] ++;
                }
                else{
                    if(currentDay[1] < 12){
                        currentDay[0] = 1;
                        currentDay[1]++;
                        newDay[0] = 1;
                        newDay[1]++;
                        selectedDays.add(newDay);
                        currentDay[0] ++;
                    }
                    else{
                        currentDay[1] = 1;
                        currentDay[0] = 1;
                        newDay[1] = 1;
                        newDay[0] = 1;
                        selectedDays.add(newDay);
                        currentDay[0] ++;
                    }
                }
            }
        }
        else if(Arrays.binarySearch(months30, currentDay[1]) > 0){
            for(int i = 0; i < nDays; i++){
                int[] newDay = {currentDay[0], currentDay[1]};
                if(currentDay[0] <= 30){
                    selectedDays.add(newDay);
                    currentDay[0] ++;
                }
                else{
                    currentDay[0] = 1;
                    currentDay[1]++;
                    newDay[0] = 1;
                    newDay[1]++;
                    selectedDays.add(newDay);
                    currentDay[0] ++;
                }
            }
        }
        else{    
            if( year%4 == 0 && (year%100 != 0 || (year%100 == 0 && year%400 == 0) ) ){
                for(int i = 0; i < nDays; i++){
                    int[] newDay = {currentDay[0], currentDay[1]};
                    if(currentDay[0] <= 29){
                        selectedDays.add(newDay);
                        currentDay[0]++;
                    }
                    else{
                        currentDay[0] = 1;
                        currentDay[1]++;
                        newDay[0] = 1;
                        newDay[1]++;
                        selectedDays.add(newDay);
                        currentDay[0] ++;
                    }
                }
            }
            else{
                for(int i = 0; i < nDays; i++){
                    int[] newDay = {currentDay[0], currentDay[1]};
                    if(currentDay[0] <= 28){
                        selectedDays.add(newDay);
                        currentDay[0]++;
                    }
                    else{
                        currentDay[0] = 1;
                        currentDay[1] ++;
                        newDay[0] = 1;
                        newDay[1]++;
                        selectedDays.add(newDay);
                        currentDay[0] ++;
                    }
                } 
            }
        }
        return selectedDays;
    }
    public boolean isClean(){
        return apixuCache.isEmpty() && darkskyCache.isEmpty();
    }
    
    @Scheduled(fixedRate = 5000)
    public void cleanUp(){
        apixuCache.clear();
        darkskyCache.clear();
    }
}
