package com.learning.randomuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    // Defining Widgets //
    TextView details, name;
    ImageView profile;
    ImageButton birthDate, location, mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_details);

        // Getting Values From Clicked Position Of Button "Check Out" //
        Intent intent = getIntent();


        name = findViewById(R.id.detailed_name);
        details = findViewById(R.id.details);
        profile = findViewById(R.id.imageView);

        birthDate = findViewById(R.id.birthdate);
        location = findViewById(R.id.location);
        mobileNumber = findViewById(R.id.phonenumber);


        // On Cake Icon Click //
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setText(intent.getStringExtra(MainActivity.EXTRA_BIRTHDATE));
            }
        });

        // On Location Icon Click //
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setText(intent.getStringExtra(MainActivity.EXTRA_LOCATION));
            }
        });

        // On Telephone Icon Click //
        mobileNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setText(intent.getStringExtra(MainActivity.EXTRA_PHONENUMBER));
            }
        });

        // Setting Name And Profile Picture Of Clicked User //
        name.setText(intent.getStringExtra(MainActivity.EXTRA_NAME));
        Picasso.get()
                .load(intent.getStringExtra(MainActivity.EXTRA_IMGURL))
                .resize(300, 300)
                .centerCrop()
                .into(profile);

    }

}