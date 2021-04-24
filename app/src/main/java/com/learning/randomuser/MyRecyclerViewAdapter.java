package com.learning.randomuser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyContentHolder> {

    // Getting Parent Context For Starting Intent //
    private Context context;


    // Getting List of ContentManager from Constructor //
    private ArrayList<ContentManger> contentMangerArrayList;

    // Getting Above Values From Constructor //
    public MyRecyclerViewAdapter(Context context, ArrayList<ContentManger> contentMangerArrayList) {
        this.context = context;
        this.contentMangerArrayList = contentMangerArrayList;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflating Layout //
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyContentHolder holder, int position) {

        // Getting Values Of Clicked Item //
        ContentManger info = contentMangerArrayList.get(position);

        String imgurl = info.getImage_url();
        String name = info.getName();
        String location = info.getLocation();
        String birthdate = info.getBirthdate();
        String number = info.getNumber();
        int age = info.getAge();

        // Setting User Profile Using Picasso Library //
        Picasso.get()
                .load(imgurl)
                .resize(200, 240)
                .centerCrop()
                .into(holder.profile_img);

        holder.username.setText(name);
        holder.age.setText("Age: " + age);
        holder.check_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sending Clicked User's Values To DetailsActivity To Show Them In Detail //
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_NAME, name);
                intent.putExtra(MainActivity.EXTRA_AGE, age);
                intent.putExtra(MainActivity.EXTRA_IMGURL, imgurl);
                intent.putExtra(MainActivity.EXTRA_LOCATION, location);
                intent.putExtra(MainActivity.EXTRA_BIRTHDATE, birthdate);
                intent.putExtra(MainActivity.EXTRA_PHONENUMBER, number);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentMangerArrayList.size();
    }

    public class MyContentHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public TextView age;
        public ImageView profile_img;
        public Button check_user;

        public MyContentHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.Name);
            age = itemView.findViewById(R.id.Age);
            profile_img = itemView.findViewById(R.id.profile_);
            check_user = itemView.findViewById(R.id.button);
        }
    }
}
