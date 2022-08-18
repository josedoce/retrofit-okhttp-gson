package github.josedoce.retrofit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {
    public static OkHttpClient client(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

    }

    public static Gson gson(){
        return new GsonBuilder().create();
    }

    public static Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://10.0.0.109:3333")
                .client(client())
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build();
        /**
         * line 30 - it's base url server
         * line 31 - it's responsible to connect to the http protocol
         * line 32 - it's responsible to converter the json to objects or vice versa.
         * line 33 - it's build pattern
         * */
    }

    public static ApiService apiService(){
        return retrofit()
                .create(ApiService.class);
    }
}
