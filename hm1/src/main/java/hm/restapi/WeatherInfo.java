package hm.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {
    private String summary;
    private Double temperatureMin;
    private Double temperatureMax;
    private int[] day;

    public int[] getDay() {
        return day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }
    
    public String getSummary() {
        return summary;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" + "summary=" + summary + ", temperatureMin=" + temperatureMin + ", temperatureMax=" + temperatureMax + ", day=" + day[0] + " "+ day[1]+ '}';
    }
     
    
}
