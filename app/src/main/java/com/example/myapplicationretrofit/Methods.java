package com.example.myapplicationretrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Methods {

    @GET("api/users?page=2")
    Call<Model> getAllData();

    @FormUrlEncoded
    @POST("api/users")
    Call<PostModel> getUserInformation(@Field("name") String name, @Field("job") String job);

    @Headers({"Content-Type: application/json"})
    @PUT("api/users/2")
    Call<PostModel> putUser(@Body PostModel model);

    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Path("id") String id);




}
