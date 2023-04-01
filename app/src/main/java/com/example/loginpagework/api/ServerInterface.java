package com.example.loginpagework.api;

import com.example.loginpagework.pojo.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServerInterface {


    //  String LOGIN_URL = "https://kunalbhargava.000webhostapp.com/gqa/";
    String LOGIN_URL = "https://genericworld.co.in/genericworld.co.in/team1/";
    //String LOGIN_URL = "http://sammankar.infinityfreeapp.com/app/";
    // String LOGIN_URL = "http://harish.42web.io/retrofit/";
    @FormUrlEncoded
    @POST("a1.php")
    Call<User> getUserLogin(
            @Field("email_id") String email_id,
            @Field("password") String password
    );




}

