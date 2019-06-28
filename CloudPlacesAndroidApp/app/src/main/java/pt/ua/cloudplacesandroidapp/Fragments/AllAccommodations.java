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
import java.util.List;

import pt.ua.cloudplacesandroidapp.API.CommunicationWithAPI;
import pt.ua.cloudplacesandroidapp.API.House;
import pt.ua.cloudplacesandroidapp.Adapters.AccommodationAdapter;
import pt.ua.cloudplacesandroidapp.ApiClient;
import pt.ua.cloudplacesandroidapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAccommodations extends Fragment{

    private RecyclerView mRecyclerView;
    private ArrayList<House> accommodations;
    private AccommodationAdapter accommodationAdapter;
    public Context mContext;
    private RecyclerView.LayoutManager mLayoutManager;
    private View currentView;

    //Search view
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    CommunicationWithAPI apiInterface;

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

        apiInterface = ApiClient.getClient().create(CommunicationWithAPI.class);
        Call<List<House>> call = apiInterface.getProperties();
        call.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                List<House> houses = response.body();
                //Update all the information
                accommodations = new ArrayList<>();

                for (House h : houses){
                    accommodations.add(h);
                }

                accommodationAdapter.setData(accommodations);
                accommodationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {

            }
        });

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
                    ArrayList<House> temp = new ArrayList<>();
                    for (House h : accommodations)
                        if (h.getName().toLowerCase().startsWith(s.toLowerCase()))
                            temp.add(h);

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
                    ArrayList<House> temp = new ArrayList<>();
                    for (House h : accommodations)
                        if (h.getName().toLowerCase().startsWith(s.toLowerCase()))
                            temp.add(h);

                    accommodationAdapter.setData(temp);
                    accommodationAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}
