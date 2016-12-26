package com.example.abed.skipe.model;

/**
 * Created by abed_eid on 03/12/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Student  extends RealmObject{


    @SerializedName("id")
    public String id;
    @SerializedName("student_name")
    public String studentName;
    @SerializedName("student_email")
    public String studentEmail;

    @SerializedName("student_password")
    public String studentPassword;
    @SerializedName("student_year")
    public String studentYear;
    @SerializedName("student_section")
    public String studentSection;
    @SerializedName("student_depart")
    public String studentDepart;





}