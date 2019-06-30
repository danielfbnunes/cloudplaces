package pt.ua.cloudplacesandroidapp.API;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommunicationWithAPI {
    @GET("get_properties/")
    Call<List<House>> getProperties();

    @POST("authenticate/")
    Call<User> authenticateUser(@Body Map<String, String> credentials);

    @GET("reloaddb/")
    Call<Void> reloadDatabase();

}
