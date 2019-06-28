package pt.ua.cloudplacesandroidapp.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommunicationWithAPI {
    @GET("get_properties/")
    Call<List<House>> getProperties();

}
