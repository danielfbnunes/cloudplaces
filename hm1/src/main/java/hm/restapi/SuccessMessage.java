package hm.restapi;

import java.util.List;

public class SuccessMessage extends Message{
    
    List<WeatherInfo> data;

    public SuccessMessage(int id, List<WeatherInfo> data) {
        super(id);
        this.data = data;
    }

    public List<WeatherInfo> getData() {
        return data;
    }

    public void setData(List<WeatherInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SuccessMessage{" + "data=" + data + '}';
    }
    
    
}
