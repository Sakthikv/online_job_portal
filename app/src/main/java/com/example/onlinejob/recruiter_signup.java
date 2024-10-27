package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class recruiter_signup extends AppCompatActivity {
    Button signupbtn;
    EditText name,password,dob,phone,emailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_signup);

        signupbtn=findViewById(R.id.signupbtn);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        dob=findViewById(R.id.dob);
        phone=findViewById(R.id.phone);
        emailid=findViewById(R.id.emailid);

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
                else  if(TextUtils.isEmpty(emailid.getText().toString().trim())){
                    emailid.setError("please fill emailid");

                }

                else{
                    insertItem();
                }

            }
        });
    }


    public void insertItem() {
        final ProgressDialog loading = ProgressDialog.show(recruiter_signup.this, "Adding Item", "Please Wait", true, false);
        final String name1= name.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String dob1 = dob.getText().toString().trim();
        final String phone1 = phone.getText().toString().trim();
        final String emailid1 = emailid.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby8iJTeUk7uYXQji9k_isPfgxbSBjNBTqLGgyG90ztPhD4leCL1yy9FcE8EQUrWucA1/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(recruiter_signup.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), recruiter_login.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(recruiter_signup.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("emailid", emailid1);

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