package com.learning.randomuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TAG For Logging On Logcat //
    final String TAG = "harry";

    // Key For Intent Values //
    public static final String EXTRA_NAME = "com.learning.randomuser.NAME";
    public static final String EXTRA_AGE = "com.learning.randomuser.AGE";
    public static final String EXTRA_IMGURL = "com.learning.randomuser.IMGURL";
    public static final String EXTRA_LOCATION = "com.learning.randomuser.LOCATION";
    public static final String EXTRA_BIRTHDATE = "com.learning.randomuser.BIRTHDATE";
    public static final String EXTRA_PHONENUMBER = "com.learning.randomuser.MOBILENUMBER";


    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<ContentManger> contentMangerArrayList;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting Up Recycler View //
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        // initializing ContentManagerArray //
        contentMangerArrayList = new ArrayList<>();

        // Creating new requestQueue
        requestQueue = Volley.newRequestQueue(this);

    }

    public void addUser(View view) {                                                        // Random User API -> www.randomuser.com //
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://randomuser.me/api/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Getting Whole API Data //
                    JSONArray results = response.getJSONArray("results");
                    JSONObject jsonObject = results.getJSONObject(0);

                    // Getting JSON Objects From API //
                    JSONObject name = jsonObject.getJSONObject("name");
                    JSONObject location = jsonObject.getJSONObject("location");
                    JSONObject date_of_birth = jsonObject.getJSONObject("dob");
                    JSONObject image = jsonObject.getJSONObject("picture");

                    // Pulling Strings and Int Values From Above Objects //

                    // Getting URL Because Later Download The Picture With Picasso Library//
                    String img_url = image.getString("large");

                    int age = date_of_birth.getInt("age");
                    String username = name.getString("title") + " " + name.getString("first") + " " + name.getString("last");
                    String number = jsonObject.getString("phone");
                    String dateofbirth = date_of_birth.getString("date");
                    String residence = location.getString("city") + " " + location.getString("state") + " " + location.getString("country") + " " + location.getInt("postcode");
                    String gender = jsonObject.getString("gender");

                    // Sending Values To Adapter //
                    contentMangerArrayList.add(new ContentManger(username, number, age, img_url, residence, dateofbirth));
                    myRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity.this, contentMangerArrayList);
                    recyclerView.setAdapter(myRecyclerViewAdapter);

                // Catching JSONException If Occurs //
                } catch (JSONException e) {

                    // Showing Error To Logcat And Toast To User //
                    Log.d(TAG, "onResponse: " + e.toString());
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }


            }
        },

        // API Refused or Some Error Occurs //
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse:  something went wrong" + error);
                Toast.makeText(MainActivity.this, "Turn On The Internet", Toast.LENGTH_SHORT).show();
            }
        });

        // Finally Adding a request queue //
        requestQueue.add(jsonObjectRequest);
    }
}