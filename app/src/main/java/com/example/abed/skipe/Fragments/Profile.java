package com.example.abed.skipe.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abed.skipe.R;
import com.example.abed.skipe.imageCircle.CircleTransform;
import com.example.abed.skipe.model.users;
import com.example.abed.skipe.utils.Session;
import com.squareup.picasso.Picasso;


public class Profile extends Fragment {



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


          ImageButton user_profile_photo = (ImageButton) view.findViewById(R.id.user_profile_photo);
          ImageView header_cover_image = (ImageView) view.findViewById(R.id.header_cover_image);
          TextView user_profile_name=(TextView)view.findViewById(R.id.user_profile_name);
          TextView  user_profile_email=(TextView)view.findViewById(R.id.user_profile_email);
          final TextView  updateProfile=(TextView)view.findViewById(R.id.updateProfile);
          TextView  aboutUs=(TextView)view.findViewById(R.id.aboutUs);

         


        users users = Session.getInstance().getUser();
        if (users != null) {

            user_profile_name.setText( users.name);
            user_profile_email.setText(users.email.toString());
            Picasso.with(getContext()).load("http://fci-suze.esy.es/Webservices/uploads/Profile_img/profile.png").transform(new CircleTransform()).into(user_profile_photo);
            user_profile_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "http://fci-suze.esy.es/Webservices/uploads/Profile_img/profile.png", Toast.LENGTH_SHORT).show();
                }});


            header_cover_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "http://fci-suze.esy.es/Webservices/uploads/Profile_img/profile.png", Toast.LENGTH_SHORT).show();

                }
            });
            updateProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = (getActivity()).getFragmentManager();
                    CheckPasswordDialog dFragment = new CheckPasswordDialog();
                    dFragment.show(fm, "Dialog Fragment");
                    Toast.makeText(getContext(), "dfoj", Toast.LENGTH_SHORT).show();
                }
            });




            aboutUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "abedeidabedhussien@gmail.com", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();



        }



        return view;
    }

}
