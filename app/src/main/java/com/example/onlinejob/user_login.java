package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

public class user_login extends AppCompatActivity {
    TextView signup_text;
    EditText username,userpassword;
    Button loginbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        signup_text = findViewById(R.id.signup_text);
        loginbtn = findViewById(R.id.userloginbtn);
        username=findViewById(R.id.username);
        userpassword=findViewById(R.id.userpassword);
        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_login.this, user_signup.class);
                startActivity(intent);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(username.getText().toString().trim())){
                    username.setError("please fill name");

                }
                else if(TextUtils.isEmpty(userpassword.getText().toString().trim())){
                    userpassword.setError("please fill password");

                }


               else {
                    retrieveData();
                }
            }
        });
    }

    public void retrieveData () {
        final ProgressDialog loading = ProgressDialog.show(user_login.this, "Retrieving Data", "Please Wait", true, false);
        String userName= username.getText().toString().trim();
        String userPassword= userpassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycby4oAMCl2CJBKeK7zS_cmntzowZjYgzfzC4oGw1nuwA3wICrSwDFqdTs3UljoQfc9vx/exec?action=get",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        // Pass the response data to the new activity
                        Gson gson = new Gson();
                        Data data = gson.fromJson(response, Data.class);

                        // Extract the list of employees
                        List<Employee> employeeList = data.getItems();

                        // Now employeeList contains the list of Employee objects
                        // You can further manipulate the list as needed
                        for (Employee employee : employeeList) {

                            if(employee.getName().equals(userName) && employee.getPassword().equals(userPassword)) {
                                Toast.makeText(user_login.this, "name " + employee.getName(), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(user_login.this, user_dashboard.class);
                       intent.putExtra("username",employee.getName());
                               intent.putExtra("userpassword", employee.getPassword());
                                startActivity(intent);
                            }


                        }
                        Toast.makeText(user_login.this, "enter correct user and password", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(user_login.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        int socketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(user_login.this);
        queue.add(stringRequest);
    }
}
