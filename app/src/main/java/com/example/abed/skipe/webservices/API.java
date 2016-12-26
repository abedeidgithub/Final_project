package com.example.abed.skipe.webservices;

import com.example.abed.skipe.model.MainResponse;
import com.example.abed.skipe.model.Schedule;
import com.example.abed.skipe.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by abed_eid on 08/12/2016.
 */

public interface API {


    @POST("login-student.php")
    Call<MainResponse> loginStudent(@Body Student student);

    @POST("register-student.php")
    Call<MainResponse> registerStudent(@Body Student student);

    @POST("select-student.php")
    Call<List<Student>> getStudent(@Body Student student);

    @POST("select-schedule.php")
    Call<List<Schedule>> getSchedule(@Body Schedule schedule);

}
