package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

public class user_job_search extends AppCompatActivity {
   SearchView searchbar;
   String searchjobname;
   List<String> compaylist;
   List<String> jobnameList;
   List<String> salaryList;
   ListView listView;
   LinearLayout searchlayout;
   TextView h1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_job_search);
        searchbar=findViewById(R.id.searchBar);
        searchlayout=findViewById(R.id.job_search_layout);
        h1=new TextView(this);
        searchbar.clearFocus();
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchlayout.removeView(h1);
searchjobname=query.trim();
                retrieveData ();
                addItem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    private void addItem(String newText) {

        LinearLayout.LayoutParams layoutParams0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        h1.setLayoutParams(layoutParams0);

        h1.setText(newText);

        h1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        h1.setTextSize(30);


        h1.setTextColor(ContextCompat.getColor(user_job_search.this, R.color.blue));

//       h1.setTextColor(Integer.parseInt("#FF1722B6"));



        // Set username as text
        searchlayout.addView(h1);
    }


    public void retrieveData () {
        listView=findViewById(R.id.joblistsview);
        compaylist=new ArrayList<>();
        jobnameList=new ArrayList<>();
        salaryList=new ArrayList<>();


        final ProgressDialog loading = ProgressDialog.show(user_job_search.this, "Retrieving Data", "Please Wait", true, false);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwnqMMZ75Y0FMRDAUCAqfalQxeaxEaOkXwC_rWVVqe0hB-fUSZM-aYo6ylJk3C_tSPk6w/exec?action=get",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        // Pass the response data to the new activity
                        Gson gson = new Gson();
                        jobData data = gson.fromJson(response, jobData.class);

                        // Extract the list of employees
                        List<jobDetails> jobList = data.getItems();

                        // Now employeeList contains the list of Employee objects
                        // You can further manipulate the list as needed
                        customBaseAdapter customBaseAdapter = null;
                        for (jobDetails job : jobList) {


                           // Toast.makeText(user_job_search.this, "company " + job.getCompanyName(), Toast.LENGTH_SHORT).show();
                            if (job.getJobName().equals(searchjobname)) {
                                compaylist.add(job.getCompanyName());
                                jobnameList.add(job.getJobName());
                                salaryList.add(job.getSalary());

                                Toast.makeText(user_job_search.this, "name " + job.getCompanyName(), Toast.LENGTH_SHORT).show();
//                                Intent intent=new Intent(user_login.this, user_dashboard.class);
//                                intent.putExtra("username",employee.getName());
//                                intent.putExtra("userpassword", employee.getPassword());
//                                startActivity(intent);
                            }


                        }
                        Toast.makeText(user_job_search.this, "enter correct user and password", Toast.LENGTH_SHORT).show();
                        customBaseAdapter = new customBaseAdapter(getApplicationContext(), compaylist, jobnameList, salaryList);

                        listView.setAdapter(customBaseAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(user_job_search.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        int socketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(user_job_search.this);
        queue.add(stringRequest);
    }


}