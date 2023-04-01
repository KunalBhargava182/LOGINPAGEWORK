package com.example.loginpagework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpagework.api.RetrofitClient;
import com.example.loginpagework.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView_register;
    static final String KEY_EMPTY = "";
    EditText et_faculty_email, et_faculty_password;
    String email_id, password;
    public static String d_users_id,d_role_id,d_fullname,d_username,d_email_id,
            d_phone_no,d_password,d_profile_photo,d_gender,d_age,
            d_address,d_dor,d_status;

    ProgressDialog pDialog;
    Button loginbtn;
    public static String g_p_s1 = "No network connection available.";

    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = getSharedPreferences("kunal", MODE_PRIVATE);
        editor = prefs.edit();
        String users_id = prefs.getString("usersid", "");
        final String s_users_password = prefs.getString("password", "");
        //Already Loged In

        if (users_id.length() > 0 && s_users_password.length() > 0) {
            //Intent intent = new Intent (MainActivity.this, Dashboard.class);
            Intent intent = new Intent(MainActivity.this, Dashboard.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }



        ui_xmltojava_connect_L();
        validInputs_L();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isDeviceOnline()) {
                    activate_online_device();
                } else {
                    //Retrieve the data entered in the edit texts
                    email_id = et_faculty_email.getText().toString().trim();
                    password = et_faculty_password.getText().toString().trim();
                    if (validInputs_L()) {
                        login();
//                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
//                        startActivity(intent);

                    }
                }
            }
        });
    }

    private void login() {

        displayLoader();


        Call<User> call = RetrofitClient.getInstance().getMyApi().getUserLogin(email_id, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                pDialog.dismiss();
                User users = response.body();
                parseLoginData(users);

            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //By this method we will retrieve  the values from json Object
    private void parseLoginData(User response) {
        try {

            if (response.getLink().equals("true")) {

                saveInfo(response);


                Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();


                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                Intent iDashboard = new Intent(MainActivity.this, Dashboard.class);

                iDashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iDashboard);
                this.finish();
            } else {

                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                cleartext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void activate_online_device() {
        AlertDialog.Builder alert112 = new AlertDialog.Builder(this);
        alert112.setTitle("Network Error");
        // alert112.setIcon(R.drawable.logo);
        alert112.setMessage(g_p_s1);
        alert112.setPositiveButton("Activate Internet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface alert, int which) {
                //Do something
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivityForResult(settingsIntent, 9003);
                alert.dismiss();
            }
        });
        alert112.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert112.show();
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void cleartext() {
        et_faculty_email.setText("");
        et_faculty_password.setText("");
    }

    private void saveInfo(User data) {
        editor.putString("users_id", data.getUsersId());
        editor.putString("role_id", data.getRoleId());
        editor.putString("fullname", data.getFullname());
        editor.putString("username", data.getUsername());
        editor.putString("email_id", data.getEmailId());
        editor.putString("phone_no", data.getPhoneNo());
        editor.putString("password", data.getPassword());
        editor.putString("profile_photo", (String) data.getProfilePhoto());
        editor.putString("gender", (String) data.getGender());
        editor.putString("age", (String) data.getAge());
        editor.putString("address", (String) data.getAddress());
        editor.putString("dor", data.getDor());
        editor.putString("status", data.getStatus());


        //Data for Dashboard
        d_users_id = data.getUsersId().toUpperCase();
        d_role_id = data.getRoleId().toLowerCase();
        d_fullname = data.getFullname().trim();
        d_username = data.getUsername().trim();
        d_email_id = data.getEmailId().toUpperCase();
        d_phone_no = data.getPhoneNo().toLowerCase();
        d_password = data.getPassword().trim();
        d_profile_photo= (String) data.getProfilePhoto();
        d_gender = ((String) data.getGender()).toUpperCase();
        d_age = ((String) data.getAge()).toLowerCase();
        d_address = ((String) data.getAddress()).trim();
        d_dor = data.getDor().trim();
        d_status = data.getStatus().trim();

        editor.commit();
    }

    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean validInputs_L() {
        if (KEY_EMPTY.equals(email_id)) {
            et_faculty_email.setError("Email ID cannot be empty");
            et_faculty_email.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            et_faculty_password.setError("Password cannot be empty");
            et_faculty_password.requestFocus();
            return false;
        }
        return true;
    }

    private void ui_xmltojava_connect_L() {
        et_faculty_email = findViewById(R.id.facultyEmail);
        et_faculty_password = findViewById(R.id.facultyPassword);
        loginbtn = findViewById(R.id.facultyLogin);
    }
}
