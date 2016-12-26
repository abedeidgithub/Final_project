package com.example.abed.skipe.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abed.skipe.R;
import com.example.abed.skipe.model.MainResponse;
import com.example.abed.skipe.model.Student;
import com.example.abed.skipe.utils.Session;
import com.example.abed.skipe.webservices.WebService;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivityTAG";

    @BindView(R.id.img_header_logo)
    ImageView imgHeaderLogo;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.lnlt_inputs_container)
    LinearLayout lnltInputsContainer;
    @BindView(R.id.tv_dont_have_account)
    TextView tvDontHaveAccount;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.rllt_body)
    RelativeLayout rlltBody;
    @BindView(R.id.prgs_loading)
    ProgressBar prgsLoading;
    @BindView(R.id.rllt_loading)
    RelativeLayout rlltLoading;
    @BindView(R.id.activity_login)
    RelativeLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_dont_have_account, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dont_have_account:
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                // override default transation of activity
                this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);

                break;

            case R.id.btn_login:

                if (!FUtilsValidation.isEmpty(etEmail, getString(R.string.enter_email))
                        && FUtilsValidation.isValidEmail(etEmail, getString(R.string.enter_valid_email))
                        && !FUtilsValidation.isEmpty(etPassword, getString(R.string.enter_password))
                        ) {
                    setLoadingMode();

                    final Student s = new Student();
                    s.studentEmail= etEmail.getText().toString();
                    s.studentPassword=etPassword.getText().toString();
//                    // login User using Retrofit
                    WebService.getInstance().getApi().loginStudent(s).enqueue(new Callback<MainResponse>() {
                        @Override
                        public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                WebService.getInstance().getApi().getStudent(s).enqueue(new Callback<List<Student>>() {

                                    public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                                        if (response.body().size() == 1) {
//
//                                                         Student s=new Student(response.body().get(0).getId().toString(),
//                                                               response.body().get(0).getStudentName().toString(),
//                                                               response.body().get(0).getStudentEmail().toString(),
//                                                               response.body().get(0).getStudentPhoto().toString(),
//                                                               response.body().get(0).getStudentPassword().toString(),
//                                                               response.body().get(0).getStudentYear().toString(),
//                                                               response.body().get(0).getStudentSection().toString(),
//                                                               response.body().get(0).getStudentDepart().toString());

                                            final Student s = new Student();
                                            s.studentEmail = (response.body().get(0).studentEmail.toString());
                                            s.id = (response.body().get(0).id);
                                            s.studentName = (response.body().get(0).studentName.toString());
                                            s.studentPassword = (response.body().get(0).studentPassword.toString());
                                            s.studentYear = (response.body().get(0).studentYear.toString());
                                            s.studentSection = (response.body().get(0).studentSection.toString());
                                            s.studentDepart = (response.body().get(0).studentDepart.toString());

                                            Session.getInstance().loginUser(s);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Error Mulit Object reutrned " + response.body().size(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<Student>> call, Throwable t) {
                                        Log.d(TAG, t.getLocalizedMessage());

                                    }
                                });

                                Intent gotohome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(gotohome);
                                finish();

                            } else {
                                Log.d(TAG, response.body().getMessage());
                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            setNormalMode();
                        }

                        @Override
                        public void onFailure(Call<MainResponse> call, Throwable t) {
                            Log.d(TAG, t.getLocalizedMessage());

                        }
                    });


                    break;

                }
        }

    }

    // set loading layout visible and hide body layout
    public void setLoadingMode() {
        rlltLoading.setVisibility(View.VISIBLE);
        rlltBody.setVisibility(View.GONE);
    }

    // set body layout visible and hide loading layout
    public void setNormalMode() {
        rlltLoading.setVisibility(View.GONE);
        rlltBody.setVisibility(View.VISIBLE);
    }
}

