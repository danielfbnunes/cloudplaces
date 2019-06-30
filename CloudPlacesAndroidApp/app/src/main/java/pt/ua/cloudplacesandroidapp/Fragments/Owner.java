package pt.ua.cloudplacesandroidapp.Fragments;

import android.os.Bundle;
import android.util.Base64;
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

import pt.ua.cloudplacesandroidapp.API.User;
import pt.ua.cloudplacesandroidapp.R;

public class Owner extends Fragment {

    private User u;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        View view = inflater.inflate(R.layout.fragment_owner, container, false);

        ImageView personImage = (ImageView) view.findViewById(R.id.personImage);
        TextView personName = (TextView) view.findViewById(R.id.personName);
        TextView personContact = (TextView) view.findViewById(R.id.phone);
        TextView personEmail = (TextView) view.findViewById(R.id.email);


        // Load the images into the ImageView using the Glide library.
        String base64Image = u.getPhoto().split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Glide.with(getContext().getApplicationContext()).asBitmap().load(decodedString).into(personImage);
        // Load textviews
        personName.setText(u.getName());
        personContact.setText(u.getCellphone().toString());
        personEmail.setText(u.getEmail());
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(u.getName());
    }


    public Owner initializeData(User u) {
        this.u = u;
        return this;
    }
}