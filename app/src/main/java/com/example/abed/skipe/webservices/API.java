package com.example.abed.skipe.webservices;

import com.example.abed.skipe.model.MainResponse;
import com.example.abed.skipe.model.Schedule;
import com.example.abed.skipe.model.users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by abed_eid on 08/12/2016.
 */

public interface API {


    @POST("login.php")
    Call<List<MainResponse>> loginUsers(@Body users users);

    @POST("insertUser.php")
    Call<MainResponse> registerStudent(@Body users users);








    @POST("select-users.php")
    Call<List<users>> getStudent(@Body users users);

    @POST("select-schedule.php")
    Call<List<Schedule>> getSchedule(@Body Schedule schedule);

}
