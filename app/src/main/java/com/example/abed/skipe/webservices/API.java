package com.example.abed.skipe.webservices;

import com.example.abed.skipe.Fragments.PostOrAsk;
import com.example.abed.skipe.model.MainResponse;
import com.example.abed.skipe.model.Post;
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

    @POST("ask_or_post.php")
    Call<List<Post>> Posts(@Body users users);


//    @POST("upload_img.php")
//    Call<List<MainResponse>> loginStudent(@Body sentBody body);





    @POST("select-users.php")
    Call<List<users>> getStudent(@Body users users);

    @POST("select-schedule.php")
    Call<List<Schedule>> getSchedule(@Body Schedule schedule);

}
