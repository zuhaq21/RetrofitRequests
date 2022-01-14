package com.example.myapplicationretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button getData, postData, putRequest, deleteRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData = (Button) findViewById(R.id.getDataBtn);
        postData = (Button) findViewById(R.id.postDataBtn);
        putRequest = (Button) findViewById(R.id.putDataBtn);
        deleteRequest= (Button) findViewById(R.id.deleteDataBtn);

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);

        //get request
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.e(TAG, "OnResponse: code :  "+response.code());
                        ArrayList<Model.data> data = response.body().getData();
                        for (Model.data data1 : data)
                        {
                            Log.e(TAG, "OnResponse: emails:  "+data1.getEmail());
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onFaliure:  "+t.getMessage());
                    }
                });
            }
        });

        //post request
        postData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<PostModel> call = methods.getUserInformation("Zorays ul Haq","Software Engineer");
                call.enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        Log.e(TAG, "OnResponse:   "+response.code());
                        Log.e(TAG, "OnResponse:  name:  "+response.body().getName());
                        Log.e(TAG, "OnResponse:  job:  "+response.body().getJob());
                        Log.e(TAG, "OnResponse:  id:  "+response.body().getId());
                        Log.e(TAG, "OnResponse:  created at:  "+response.body().getCreatedAt());
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Log.e(TAG, "onFaliure:  "+t.getMessage());


                    }
                });
            }
        });

        //put request
        putRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                PostModel model = new PostModel();
                model.setName("Zorays");
                model.setJob("Engineer");
                Call<PostModel> call = methods.putUser(model);
                //Call<PostModel> call = methods.putUser("2", "Zorays", "Engineer");
                call.enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        if(response.isSuccessful())
                        {
                            Log.e(TAG, "OnResponse:   "+response.code());
                            Log.e(TAG, "OnResponse:  name:  "+response.body().getName());
                            Log.e(TAG, "OnResponse:  job:  "+response.body().getJob());
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Log.e(TAG, "onFaliure:  "+t.getMessage());
                    }
                });
            }
        });

        deleteRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Void> call = methods.deleteUser("2");
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful())
                        {
                            return;
                        }
                        Log.e(TAG, "OnResponse:   "+response.code());
                        Toast.makeText(getApplicationContext(), "Deleted Request Successfull with response code:  "+response.code(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e(TAG, "onFaliure:  "+t.getMessage());
                    }
                });
            }
        });

    }
}