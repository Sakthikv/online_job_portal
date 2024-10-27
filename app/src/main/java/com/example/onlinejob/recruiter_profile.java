package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recruiter_profile extends AppCompatActivity {
    Intent intent;
    String updateid="";
    String username;
    String userdob;
    String phone;
    String emailid;

    String userPassword;
    LinearLayout profileLayout;
    EditText editTextname,editTextpassword,editTextdob,editTextphone,editTextemailid;
    TextView h1;
    TextView n1;
    TextView p1;
    TextView d1;
    TextView ph1;
    TextView email1;
    Button update;
    EditText updatename,updatePassword,updatedob,updatephone,updateemailid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_profile);

        intent = getIntent();
        username = intent.getStringExtra("recruitername");
        userPassword = intent.getStringExtra("recruiterpassword");
         retrieveData ();
    }

    public void retrieveData () {
        final ProgressDialog loading = ProgressDialog.show(recruiter_profile.this, "Retrieving Data", "Please Wait", true, false);
//        String userName= username.getText().toString().trim();
//        String userPassword= userpassword.getText().toString().trim();
//
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycby8iJTeUk7uYXQji9k_isPfgxbSBjNBTqLGgyG90ztPhD4leCL1yy9FcE8EQUrWucA1/exec?action=get",
                new Response.Listener<String>() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        // Pass the response data to the new activity
                        Gson gson = new Gson();
                        recruiterData data = gson.fromJson(response, recruiterData.class);

                        // Extract the list of employees
                        List<recruiterDetails> recruiterDetailsList = data.getItems();

                        // Now employeeList contains the list of Employee objects
                        // You can further manipulate the list as needed
                        for (recruiterDetails recruiter : recruiterDetailsList) {

                            if(recruiter.getName().equals(username) && recruiter.getPassword().equals(userPassword)) {
                                Toast.makeText(recruiter_profile.this, "name " + recruiter.getName(), Toast.LENGTH_SHORT).show();

                                profileLayout = findViewById(R.id.profileLinear);
                                editTextname= new EditText(recruiter_profile.this);
                                editTextpassword = new EditText(recruiter_profile.this);
                                h1=new TextView(recruiter_profile.this);
                                editTextdob= new EditText(recruiter_profile.this);
                                editTextphone= new EditText(recruiter_profile.this);
                                editTextemailid= new EditText(recruiter_profile.this);

                                n1=new TextView(recruiter_profile.this);
                                p1=new TextView(recruiter_profile.this);
                                d1=new TextView(recruiter_profile.this);
                                ph1=new TextView(recruiter_profile.this);
                                email1=new TextView(recruiter_profile.this);
                                update=new Button(recruiter_profile.this);
                                editTextpassword.setText(userPassword);
                                LinearLayout.LayoutParams layoutParams0 = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                h1.setLayoutParams(layoutParams0);
                                String text="<b>Profile</b>";
                                h1.setText(Html.fromHtml(text));

                                h1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                h1.setTextSize(30);


                                h1.setTextColor(ContextCompat.getColor(recruiter_profile.this, R.color.blue));

//       h1.setTextColor(Integer.parseInt("#FF1722B6"));



                                // Set username as text
                                profileLayout.addView(h1);



                                LinearLayout.LayoutParams layoutParamsn = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                n1.setLayoutParams(layoutParamsn);
                                n1.setText("name");
                                n1.setPadding(0,10,0,0);
                                n1.setTextColor(ContextCompat.getColor(recruiter_profile.this, R.color.blue));
                                profileLayout.addView(n1);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                editTextname.setText(recruiter.getName());
                                editTextname.setLayoutParams(layoutParams);
                                int nid=1;
                                editTextname.setId(nid);

                                // Set username as text
                                profileLayout.addView(editTextname);

                                LinearLayout.LayoutParams layoutParamsp = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                p1.setLayoutParams(layoutParamsp);
                                p1.setText("password");
                                p1.setTextColor(ContextCompat.getColor(recruiter_profile.this, R.color.blue));
                                profileLayout.addView(p1);
                                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                int pid=2;
                                editTextpassword.setId(pid);

                                editTextpassword.setLayoutParams(layoutParams1);

                                // Set username as text

                                profileLayout.addView(editTextpassword);
                                updateid=recruiter.getId();
                                userdob=recruiter.getDob();
                                phone=recruiter.getPhone();
                                emailid=recruiter.getemaild();


                                LinearLayout.LayoutParams layoutParamsd = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                d1.setLayoutParams(layoutParamsd);
                                d1.setText("Date of birth");
                                d1.setTextColor(ContextCompat.getColor(recruiter_profile.this, R.color.blue));
                                profileLayout.addView(d1);
                                LinearLayout.LayoutParams layoutParamsdob = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                editTextdob.setText(userdob);
                                editTextname.setLayoutParams(layoutParamsdob);

                                int dobid=3;
                                editTextdob.setId(dobid);

                                // Set username as text
                                profileLayout.addView(editTextdob);

                                LinearLayout.LayoutParams layoutParamsph = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                ph1.setLayoutParams(layoutParamsph);
                                ph1.setText("phone");
                                ph1.setTextColor(ContextCompat.getColor(recruiter_profile.this, R.color.blue));
                                profileLayout.addView(ph1);
                                LinearLayout.LayoutParams layoutParamsphone = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                editTextphone.setText(phone);
                                editTextphone.setLayoutParams(layoutParamsphone);

                                int phid=4;
                                editTextname.setId(phid);


                                // Set username as text
                                profileLayout.addView(editTextphone);


                                LinearLayout.LayoutParams layoutParamsq1 = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                email1.setLayoutParams(layoutParamsq1);
                                email1.setText("Emailid");
                                email1.setTextColor(ContextCompat.getColor(recruiter_profile.this, R.color.blue));
                                profileLayout.addView(email1);
                                LinearLayout.LayoutParams layoutParamsemailid = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                editTextemailid.setText(recruiter.getemaild());
                                editTextemailid.setLayoutParams(layoutParamsemailid);
                                int qid=5;
                                editTextemailid.setId(qid);
                                // Set username as text
                                profileLayout.addView(editTextemailid);




                                LinearLayout.LayoutParams layoutParamsbutton = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                update.setText("Update");
                                update.setLayoutParams(layoutParamsbutton);
                                update.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        updateItem();
                                    }
                                });

                                // Set username as text
                                profileLayout.addView(update);






                            }


                        }
                        //  Toast.makeText(user_login.this, "enter correct user and password", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(recruiter_profile.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        int socketTimeout = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(recruiter_profile.this);
        queue.add(stringRequest);
    }


    public void updateItem() {

        final ProgressDialog loading = ProgressDialog.show(recruiter_profile.this, "Updating Item", "Please Wait", true, false);
        int nid=1,pid=2,dobid=3,phid=4,qid=5,sid=6,domid=7,exid=8;
//        updatename=findViewById(nid);
//        updatePassword=findViewById(pid);
//        updatedob=findViewById(dobid);
//        updatephone=findViewById(phid);
//        updatequalification=findViewById(qid);qid


        final String name1= editTextname.getText().toString().trim();
        final String pass = editTextpassword.getText().toString().trim();
        final String dob1 = editTextdob.getText().toString().trim();
        final String phone1 = editTextphone.getText().toString().trim();
        final String emailid2 = editTextemailid.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby8iJTeUk7uYXQji9k_isPfgxbSBjNBTqLGgyG90ztPhD4leCL1yy9FcE8EQUrWucA1/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(recruiter_profile.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), recruiter_dashboard.class);
//                        profileLayout.removeView(h1);
//                        profileLayout.removeView(n1);
//                        profileLayout.removeView(editTextname);
//                        profileLayout.removeView(p1);
//                        profileLayout.removeView(editTextpassword);
//                        profileLayout.removeView(d1);
//                        profileLayout.removeView(editTextdob);
//                        profileLayout.removeView(ph1);
//                        profileLayout.removeView(editTextphone);
//                        profileLayout.removeView(q1);
//                        profileLayout.removeView(editTextqualification);
//                        profileLayout.removeView(s1);
//                        profileLayout.removeView(editTextskills);
//                        profileLayout.removeView(dom1);
//                        profileLayout.removeView(editTextdomain);
//                        profileLayout.removeView(ex1);
//                        profileLayout.removeView(editTextexperience);
//                        profileLayout.removeView(update);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(recruiter_profile.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "update"); // Change action to update
                params.put("id",updateid);
                params.put("name", name1);
                params.put("password", pass);
                params.put("dob", dob1);
                params.put("phone", phone1);
                params.put("emailid", emailid2);

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
