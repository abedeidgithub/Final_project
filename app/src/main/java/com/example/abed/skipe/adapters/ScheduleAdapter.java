package com.example.abed.skipe.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abed.skipe.R;
import com.example.abed.skipe.model.Schedule;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abed_eid on 10/12/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {


    List<Schedule> scheduleList;
    Context context;


    public ScheduleAdapter(List<Schedule> scheduleList, Context context) {
        this.scheduleList = scheduleList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Schedule schedule = scheduleList.get(position);
        String to = (schedule.Schedule_to.substring(0, 5));
        String from = (schedule.Schedule_from.substring(0, 5));
        int to_h = Integer.parseInt(to.substring(0, 2));
        int from_h = Integer.parseInt(from.substring(0, 2));
        int from_m = Integer.parseInt(from.substring(3, 5));
        int to_m = Integer.parseInt(to.substring(3, 5));
        Calendar calander = Calendar.getInstance();
        int cHour = calander.get(Calendar.HOUR_OF_DAY);
        int cMinute = calander.get(Calendar.MINUTE);
        if (cHour >= from_h && cHour < to_h) {

            holder.time_now.setImageResource(R.drawable.ic_now);

        } else if (cHour == to_h) {
            if (cMinute <= to_m) {
                holder.time_now.setImageResource(R.drawable.ic_now);

            } else {
                holder.time_now.setImageResource(R.drawable.ic_back);

            }

        }
        if (cHour > to_h) {
            holder.time_now.setImageResource(R.drawable.ic_back);

        }


        if (to_h > 12) {
            int i = to_h - 12;
            to = "0" + i + to.substring(2, 5);
        }

        if (from_h > 12) {

            int i = from_h - 12;
            from = "0" + i + from.substring(2, 5);
        }


        holder.doctor.setText(schedule.doctor.trim());
        holder.to.setText(to.trim());
        holder.from.setText(from.trim());
        holder.place.setText(schedule.place.trim());
        holder.subject.setText(schedule.subject.trim());

    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView doctor, subject, place, from, to;
        ImageView time_now;
        CardView schedule_card;

        public MyViewHolder(View itemView) {
            super(itemView);
            doctor = (TextView) itemView.findViewById(R.id.doctor);
            subject = (TextView) itemView.findViewById(R.id.subject);
            to = (TextView) itemView.findViewById(R.id.to);
            from = (TextView) itemView.findViewById(R.id.from);
            place = (TextView) itemView.findViewById(R.id.place);
            time_now = (ImageView) itemView.findViewById(R.id.time_now);
            schedule_card = (CardView) itemView.findViewById(R.id.schedule_card);

        }
    }
}
