package github.josedoce.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import github.josedoce.retrofit.model.Nome;
import github.josedoce.retrofit.network.ApiModule;
import github.josedoce.retrofit.network.ApiService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private TextView tv_info;
    private Button bt_get_user, bt_put, bt_get_all, bt_post, bt_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ApiModule.apiService();

        setContentView(R.layout.activity_main);
        tv_info = findViewById(R.id.tv_info);

        bt_get_user = findViewById(R.id.bt_get_user);
        bt_get_all = findViewById(R.id.bt_get_all);
        bt_post = findViewById(R.id.bt_post);
        bt_put = findViewById(R.id.bt_put);
        bt_delete = findViewById(R.id.bt_delete);

        bt_get_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNome();
            }
        });

        bt_get_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllNomes();
            }
        });

        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNome();
            }
        });

        bt_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editNome();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNome();
            }
        });
    }

    private void getNome(){
        apiService.user(1).enqueue(new Callback<Nome>() {
            @Override
            public void onResponse(Call<Nome> call, Response<Nome> response) {
                if(response.isSuccessful() && response.body() != null){

                    Nome success = response.body();
                    tv_info.setText(success.toString());

                }else{
                    try {
                        String message = response.errorBody().string();
                        tv_info.setText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Nome> call, Throwable t) {
                tv_info.setText(t.getMessage());
            }
        });
    }

    private void getAllNomes(){
        apiService.allUsers().enqueue(new Callback<List<Nome>>() {
            @Override
            public void onResponse(Call<List<Nome>> call, Response<List<Nome>> response) {
                if(response.isSuccessful() && response.body() != null){

                    List<Nome> success = response.body();
                    tv_info.setText(success.toString());


                }else{


                    try {
                        String message = response.errorBody().string();

                        tv_info.setText(message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Nome>> call, Throwable t) {
                tv_info.setText(t.getMessage());
            }
        });
    }

    private void postNome(){
        Nome n1 = new Nome();
        n1.setId(5);
        n1.setIdade(23);
        n1.setNome("joão");
        String json = new Gson().toJson(n1);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=UTF-8"));

        apiService.newUser(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body() != null){
                    try {
                        String success = response.body().string();
                        //Nome nome = new Gson().fromJson(message, Nome.class);
                        tv_info.setText(success);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{


                    try {
                        String message = response.errorBody().string();

                        tv_info.setText(message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //só pega erros 500
                tv_info.setText(t.getMessage());
            }
        });
    }

    private void editNome(){
        Nome n1 = new Nome();
        n1.setId(1);
        n1.setIdade(24);
        n1.setNome("Jose");
        String json = new Gson().toJson(n1);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=UTF-8"));
        apiService.editUser(n1.getId(), requestBody).enqueue(new Callback<Nome>() {
            @Override
            public void onResponse(Call<Nome> call, Response<Nome> response) {
                if(response.isSuccessful() && response.body() != null){
                    Nome success = response.body();
                    tv_info.setText(success.toString());
                }else{

                    try {
                        String message = response.errorBody().string();
                        tv_info.setText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Nome> call, Throwable t) {
                tv_info.setText(t.getMessage());
            }
        });
    }

    private void deleteNome(){
        apiService.deleteUser(1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body() != null){
                    try {
                        String success = response.body().string();
                        //Nome nome = new Gson().fromJson(message, Nome.class);
                        tv_info.setText(success);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{


                    try {
                        String message = response.errorBody().string();

                        tv_info.setText(message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tv_info.setText(t.getMessage());
            }
        });
    }
}