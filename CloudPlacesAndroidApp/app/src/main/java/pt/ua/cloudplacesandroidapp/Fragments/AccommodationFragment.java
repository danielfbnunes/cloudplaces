package pt.ua.cloudplacesandroidapp.Fragments;

import android.os.Bundle;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import pt.ua.cloudplacesandroidapp.API.House;
import pt.ua.cloudplacesandroidapp.R;


public class AccommodationFragment extends Fragment {

    private String name, description, lot, beds, bath, garage, address;
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
        TextView accommodationBeds = (TextView) view.findViewById(R.id.accommodationBeds);
        TextView accommodationAddress = (TextView) view.findViewById(R.id.accommodationLocation);
        TextView accommodationLot = (TextView) view.findViewById(R.id.accommodationLot);
        TextView accommodationBath = (TextView) view.findViewById(R.id.accommodationBath);
        TextView accommodationGarage = (TextView) view.findViewById(R.id.accommodationGarage);

        // Load the images into the ImageView using the Glide library.
        String base64Image = accomodation.getPhotos().get(0).getPhoto().split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Glide.with(getContext().getApplicationContext()).asBitmap().load(decodedString).into(accommodationImage);
        // Load textviews
        accommodationName.setText(name);
        accommodationDescription.setText(description);
        accommodationBeds.setText(beds);
        accommodationAddress.setText(address);
        accommodationLot.setText(lot);
        accommodationBath.setText(bath);
        accommodationGarage.setText(garage);

        Button contactTheOwner = (Button) view.findViewById(R.id.contactTheOwner);
        contactTheOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new Owner().initializeData(accomodation.getUser());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).addToBackStack(null).commit();
            }
        });

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
        this.description = accomodation.getDescription();
        this.address = accomodation.getAddress();
        this.lot = accomodation.getHabSpace().toString();
        this.bath = accomodation.getNbathrooms().toString();
        this.beds = accomodation.getNrooms().toString();
        this.garage = accomodation.getGarage().toString();
        return this;
    }
}