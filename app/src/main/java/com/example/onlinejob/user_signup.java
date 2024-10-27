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

import java.util.HashMap;
import java.util.Map;

public class user_signup extends AppCompatActivity {
  Button signupbtn;
  EditText name,password,dob,phone,qualification,skills,domain,experience;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        signupbtn=findViewById(R.id.signupbtn);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        dob=findViewById(R.id.dob);
        phone=findViewById(R.id.phone);
        qualification=findViewById(R.id.qualification);
        skills=findViewById(R.id.skills);
        domain=findViewById(R.id.domain);
        experience=findViewById(R.id.experience);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString().trim())){
                    name.setError("please fill name");

                }
               else if(TextUtils.isEmpty(password.getText().toString().trim())){
                    password.setError("please fill password");

                }
                else if(TextUtils.isEmpty(dob.getText().toString().trim())){
                    dob.setError("please fill dob");

                }
               else if(TextUtils.isEmpty(phone.getText().toString().trim())){
                    phone.setError("please fill phone");

                }
             else  if(TextUtils.isEmpty(qualification.getText().toString().trim())){
                    qualification.setError("please fill qualification");

                }
                else if(TextUtils.isEmpty(skills.getText().toString().trim())){
                    skills.setError("please fill skills");

                }
               else if(TextUtils.isEmpty(domain.getText().toString().trim())){
                    domain.setError("please fill domain");

                }
                else if(TextUtils.isEmpty(experience.getText().toString().trim())){
                    experience.setError("please fill experience");

                }
                else {

                    insertItem();
                }

            }
        });


    }


    public void insertItem() {
        final ProgressDialog loading = ProgressDialog.show(user_signup.this, "Adding Item", "Please Wait", true, false);
        final String name1= name.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String dob1 = dob.getText().toString().trim();
        final String phone1 = phone.getText().toString().trim();
        final String qualification1 = qualification.getText().toString().trim();
        final String skills1 = skills.getText().toString().trim();
        final String domain1 = domain.getText().toString().trim();
        final String experience1 = experience.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby4oAMCl2CJBKeK7zS_cmntzowZjYgzfzC4oGw1nuwA3wICrSwDFqdTs3UljoQfc9vx/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(user_signup.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), user_login.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(user_signup.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "create");
                params.put("name", name1);
                params.put("password", pass);
                params.put("dob", dob1);
                params.put("phone", phone1);
                params.put("qualification", qualification1);
                params.put("skills", skills1);
                params.put("domain", domain1);
                params.put("experience", experience1);
                return params;
            }
        };

        int socketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}