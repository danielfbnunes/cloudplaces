package pt.ua.cloudplacesandroidapp.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import pt.ua.cloudplacesandroidapp.API.House;
import pt.ua.cloudplacesandroidapp.R;


public class AccommodationFragment extends Fragment {

    private String name;
    private House accomodation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar)  getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        View view = inflater.inflate(R.layout.fragment_accommodation, container, false);

        ImageView accommodationImage = (ImageView)  view.findViewById(R.id.accommodationImage);
        TextView accommodationName = (TextView) view.findViewById(R.id.accommodationName);
        TextView accommodationDescription = (TextView) view.findViewById(R.id.accommodationDescription);

        // Load the images into the ImageView using the Glide library.
        Glide.with(getContext().getApplicationContext()).load("https://i.kinja-img.com/gawker-media/image/upload/s--bIV3xkEm--/c_scale,f_auto,fl_progressive,q_80,w_800/jsprifdd1gmfy7e7nola.jpg").into(accommodationImage);
        // Load textviews
        accommodationName.setText(name);

        //TODO: add more info about the current accommodation
        //accommodationDescription.setText(bio);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(name);
    }


    public AccommodationFragment initializeData(House accomodation){

        this.accomodation = accomodation;
        this.name = accomodation.getName();
        //TODO: add more info about the current accommodation
        //this.bio=p.getBio();
        return this;
    }
}