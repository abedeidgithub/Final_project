package com.example.abed.skipe.activites;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abed.skipe.R;
import com.example.abed.skipe.adapters.CommentAdapter;
import com.example.abed.skipe.adapters.PostsAdapter;
import com.example.abed.skipe.model.CommentModel;
import com.example.abed.skipe.model.Post;
import com.example.abed.skipe.model.users;
import com.example.abed.skipe.utils.Session;
import com.example.abed.skipe.webservices.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment extends Activity {

    private CommentAdapter adapter;
    RecyclerView recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view_comments);
        CommentModel post=new CommentModel();
        post.ask_or_post_id=getIntent().getStringExtra("Post_ID");
            WebService.getInstance().getApi().Commrnts(post).enqueue(new Callback<List<CommentModel>>() {
                @Override
                public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                    List<CommentModel> commentModels =response.body();


                    adapter=new CommentAdapter(commentModels,Comment.this);
                    recycler_view.setLayoutManager(new LinearLayoutManager(Comment.this));
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                    Toast.makeText(Comment.this, "error users is null  ", Toast.LENGTH_SHORT).show();

                }
            });


    }
}
