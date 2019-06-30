package pt.ua.cloudplacesandroidapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pt.ua.cloudplacesandroidapp.API.House;
import pt.ua.cloudplacesandroidapp.Fragments.AccommodationFragment;
import pt.ua.cloudplacesandroidapp.R;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.ViewHolder>{
    //Member variables.
    private ArrayList<House> accommodations;
    private Context mContext;
    private Activity mActivity;

    public AccommodationAdapter(Context context, ArrayList<House> accommodations, Activity mActivity){
        this.accommodations = accommodations;
        this.mContext = context;
        this.mActivity = mActivity;
    }

    public void setData(ArrayList<House> accommodations){
        this.accommodations = accommodations;
    }

    @Override
    public AccommodationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.accommodation_item, parent, false));
    }

    public void onBindViewHolder(AccommodationAdapter.ViewHolder holder, int position){
        // Get current sport.
        if(accommodations != null) {
            House currentAccommodation = accommodations.get(position);
            // Populate the textviews with data.
            holder.bindTo(currentAccommodation);
        }
    }

    public int getItemCount(){
        if(accommodations != null)
            return accommodations.size();
        else
            return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Member Variables for the TextViews
        private TextView name;
        private TextView price;
        private TextView location;
        private TextView lot;
        private TextView beds;
        private TextView bath;
        private TextView garage;

        private ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            // Initialize the views
            imageView = itemView.findViewById(R.id.accommodationImage);
            name = itemView.findViewById(R.id.accommodationName);
            price = itemView.findViewById(R.id.accommodationPrice);
            location = itemView.findViewById(R.id.accommodationLocation);
            lot = itemView.findViewById(R.id.accommodationLot);
            beds = itemView.findViewById(R.id.accommodationBeds);
            bath = itemView.findViewById(R.id.accommodationBath);
            garage = itemView.findViewById(R.id.accommodationGarage);
            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(House h){
            name.setText(h.getName());
            price.setText(h.getPrice().toString() + " €");
            location.setText(h.getAddress());
            lot.setText(h.getHabSpace().toString());
            beds.setText(h.getNrooms().toString());
            bath.setText(h.getNbathrooms().toString());
            garage.setText(h.getGarage().toString());
            String base64Image = h.getPhotos().get(0).getPhoto().split(",")[1];
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Glide.with(mContext).asBitmap().load(decodedString).into(imageView);
        }

        public void onClick(View view){
            House a = accommodations.get(getAdapterPosition());
            AccommodationFragment accommodation = new AccommodationFragment().initializeData(a);
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, accommodation).addToBackStack(null).commit();
        }

    }
}
