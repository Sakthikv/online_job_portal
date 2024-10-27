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

public class candidate_page extends AppCompatActivity {
    SearchView searchbar;
    String searchjobname;
    List<String> namelist;
    List<String> dobList;
    List<String> phoneList;
    List<String> qualificationList;
    List<String> skillsList;
    List<String> domainList;
    List<String> experienceList;
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


        h1.setTextColor(ContextCompat.getColor(candidate_page.this, R.color.blue));

//       h1.setTextColor(Integer.parseInt("#FF1722B6"));



        // Set username as text
        searchlayout.addView(h1);
    }


    public void retrieveData () {
        listView=findViewById(R.id.joblistsview);
        namelist=new ArrayList<>();
        dobList=new ArrayList<>();
        phoneList=new ArrayList<>();
        qualificationList=new ArrayList<>();
       skillsList=new ArrayList<>();
      domainList=new ArrayList<>();
        experienceList=new ArrayList<>();


        final ProgressDialog loading = ProgressDialog.show(candidate_page.this, "Retrieving Data", "Please Wait", true, false);


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
                        customBaseAdapter1 customBaseAdapter = null;
                        for (Employee employee : employeeList) {


                            // Toast.makeText(user_job_search.this, "company " + job.getCompanyName(), Toast.LENGTH_SHORT).show();
                            if (employee.getDomain().equals(searchjobname)) {
                                namelist.add(employee.getName());
                                dobList.add(employee.getDob());
                                phoneList.add(employee.getPhone());
                                qualificationList.add(employee.getQualification());
                                skillsList.add(employee.getSkills());
                                domainList.add(employee.getDomain());
                                experienceList.add(employee.getExperience());

                                Toast.makeText(candidate_page.this, "name " + employee.getName(), Toast.LENGTH_SHORT).show();
//                                Intent intent=new Intent(user_login.this, user_dashboard.class);
//                                intent.putExtra("username",employee.getName());
//                                intent.putExtra("userpassword", employee.getPassword());
//                                startActivity(intent);
                            }


                        }
                        Toast.makeText(candidate_page.this, "enter correct user and password", Toast.LENGTH_SHORT).show();
                        customBaseAdapter = new customBaseAdapter1(getApplicationContext(), namelist, domainList,qualificationList);

                        listView.setAdapter(customBaseAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(candidate_page.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        int socketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(candidate_page.this);
        queue.add(stringRequest);
    }


}