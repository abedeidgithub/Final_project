package com.example.abed.skipe.Fragments;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
                    getImage();
                }});


            header_cover_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ////

                    getImage();

                    ////

                }
            });
            updateProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = (getActivity()).getFragmentManager();
                    CheckPasswordDialog dFragment = new CheckPasswordDialog();
                    dFragment.show(fm, "Dialog Fragment");
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

    public void getImage (){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog);
        LinearLayout cameraLayout= (LinearLayout) dialog.findViewById(R.id.linearLayout);
        LinearLayout galleryLaout= (LinearLayout) dialog.findViewById(R.id.gal_layout);
        TextView cancel= (TextView) dialog.findViewById(R.id.dialog_cancel_txt);
        cameraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,2);
                dialog.dismiss();
            }
        });
        galleryLaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,getString(R.string.select_picture)), 1);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
