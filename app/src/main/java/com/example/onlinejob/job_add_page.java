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

public class job_add_page extends AppCompatActivity {
    Button addbtn;
    EditText companyname,jobname,salary;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add_page);

        addbtn=findViewById(R.id.addbtn);
        companyname=findViewById(R.id.companyname);
        jobname=findViewById(R.id.jobName);
        salary=findViewById(R.id.salary);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(companyname.getText().toString().trim())){
                    companyname.setError("please fill company name");

                }
                else if(TextUtils.isEmpty(jobname.getText().toString().trim())){
                    jobname.setError("please fill job name");

                }
                else if(TextUtils.isEmpty(salary.getText().toString().trim())){
                    salary.setError("please fill salary");

                }


                else
                insertItem();

            }
        });
    }


    public void insertItem() {
        final ProgressDialog loading = ProgressDialog.show(job_add_page.this, "Adding Item", "Please Wait", true, false);
        final String companyname1= companyname.getText().toString().trim();
        final String jobname1 =jobname.getText().toString().trim();
        final String salary1 = salary.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwnqMMZ75Y0FMRDAUCAqfalQxeaxEaOkXwC_rWVVqe0hB-fUSZM-aYo6ylJk3C_tSPk6w/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(job_add_page.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), job_add_page.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(job_add_page.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "create");
                params.put("companyName", companyname1);
                params.put("jobName", jobname1);
                params.put("salary", salary1);


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