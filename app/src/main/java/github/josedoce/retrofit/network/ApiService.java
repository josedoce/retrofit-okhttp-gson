package github.josedoce.retrofit.network;

import java.util.List;

import github.josedoce.retrofit.model.Nome;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/")
    Call<ResponseBody> newUser(@Body RequestBody requestBody);

    @GET("/")
    Call<List<Nome>> allUsers();

    @GET("/{id}")
    Call<Nome> user(@Path("id") int id);

    @PUT("/{id}")
    Call<Nome> editUser(@Path("id") int id, @Body RequestBody requestBody);

    @DELETE("/delete/{id}")
    Call<ResponseBody> deleteUser(@Path("id") int id);
}
