package com.example.abed.skipe.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abed.skipe.R;
import com.example.abed.skipe.adapters.ScheduleAdapter;
import com.example.abed.skipe.fcm.FirebaseService;
import com.example.abed.skipe.model.Schedule;
import com.example.abed.skipe.model.Student;
import com.example.abed.skipe.utils.Session;
import com.example.abed.skipe.webservices.WebService;
import com.fourhcode.forhutils.FUtilsProgress;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleFragment extends Fragment {
    private final String TAG = "ScheduleFragmentTAG";

    private ScheduleAdapter adapter;
    RecyclerView recycler_view;
    RelativeLayout rllt_loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        RelativeLayout r = (RelativeLayout) view.findViewById(R.id.Schedule_Re);
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading ...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        recycler_view = (RecyclerView) view.findViewById((R.id.recycler_view));
        rllt_loading = (RelativeLayout) view.findViewById((R.id.rllt_loading));
        Student student = Session.getInstance().getUser();
        if (student != null) {
            Student user = Session.getInstance().getUser();
            String studentYear = user.studentYear;
            String studentDepart = user.studentDepart;
            String studentSection = user.studentSection;
            FirebaseMessaging.getInstance().subscribeToTopic(studentYear+studentDepart+studentSection);
            Log.e("MessageR","sss");
            WebService.getInstance().getApi().getSchedule(new Schedule(student.studentYear, student.studentDepart, student.studentSection)).enqueue(new Callback<List<Schedule>>() {
                @Override
                public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                    List<Schedule> scheduleList = response.body();
                    adapter = new ScheduleAdapter(scheduleList, getContext());
                    recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.setAdapter(adapter);

                    dialog.dismiss();

                }

                @Override
                public void onFailure(Call<List<Schedule>> call, Throwable t) {

                }
            });
//            setNormalMode();
        } else {
            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
        }


        return view;
    }


}
