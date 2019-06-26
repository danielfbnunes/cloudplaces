package pt.ua.cloudplacesandroidapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;

import pt.ua.cloudplacesandroidapp.Adapters.AccommodationAdapter;
import pt.ua.cloudplacesandroidapp.R;

public class AllAccommodations extends Fragment{

    private RecyclerView mRecyclerView;
    private ArrayList<String> accommodations;
    private AccommodationAdapter accommodationAdapter;
    public Context mContext;
    private RecyclerView.LayoutManager mLayoutManager;
    private View currentView;

    //Search view
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //create comparator
        class AccommodationComparator implements Comparator<String> {
            @Override
            public int compare(String a1, String a2) {return a1.compareTo(a2);}
        }

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.setDrawerListener(toggle);

        View view = inflater.inflate(R.layout.fragment_show_accommodations, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.accommodations_list);

        accommodationAdapter = new AccommodationAdapter(getActivity(), accommodations, getActivity());

        final AccommodationAdapter adapter =  accommodationAdapter;

        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);

        //Update all the information
        accommodations = new ArrayList<>();

        //TODO: remove this step. Only for testing
        accommodations.add("Casa 3");
        accommodations.add("Casa 2");
        accommodations.add("Casa 1");

        accommodationAdapter.setData(accommodations);
        accommodationAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.currentView = view;
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.title_accommodations));

        searchView = (SearchView) getActivity().findViewById(R.id.search_accommodation);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.equals("") || s.equals(" "))
                {
                    accommodationAdapter.setData(accommodations);
                    accommodationAdapter.notifyDataSetChanged();
                }
                else
                {
                    ArrayList<String> temp = new ArrayList<>();
                    for (String a : accommodations)
                        if (a.toLowerCase().startsWith(s.toLowerCase()))
                            temp.add(a);

                    accommodationAdapter.setData(temp);
                    accommodationAdapter.notifyDataSetChanged();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("") || s.equals(" "))
                {
                    accommodationAdapter.setData(accommodations);
                    accommodationAdapter.notifyDataSetChanged();
                }
                else
                {
                    ArrayList<String> temp = new ArrayList<>();
                    for (String a : accommodations)
                        if (a.toLowerCase().startsWith(s.toLowerCase()))
                            temp.add(a);

                    accommodationAdapter.setData(temp);
                    accommodationAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}