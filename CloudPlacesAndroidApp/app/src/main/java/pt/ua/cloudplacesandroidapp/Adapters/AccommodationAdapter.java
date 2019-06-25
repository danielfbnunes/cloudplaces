package pt.ua.cloudplacesandroidapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pt.ua.cloudplacesandroidapp.Fragments.AccommodationFragment;
import pt.ua.cloudplacesandroidapp.R;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.ViewHolder>{
    //Member variables.
    private ArrayList<String> accommodations;
    private Context mContext;
    private Activity mActivity;

    public AccommodationAdapter(Context context, ArrayList<String> accommodations, Activity mActivity){
        this.accommodations = accommodations;
        this.mContext = context;
        this.mActivity = mActivity;
    }

    public void setData(ArrayList<String> accommodations){
        this.accommodations = accommodations;
    }

    @Override
    public AccommodationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.accommodation_item, parent, false));
    }

    public void onBindViewHolder(AccommodationAdapter.ViewHolder holder, int position){
        // Get current sport.
        if(accommodations != null) {
            String currentAccommodation = accommodations.get(position);
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
        private TextView textview;
        private ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            // Initialize the views.
            textview = itemView.findViewById(R.id.accommodationName);
            imageView = itemView.findViewById(R.id.accommodationImage);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(String a){
            textview.setText(a);
            Glide.with(mContext).load("https://i.kinja-img.com/gawker-media/image/upload/s--bIV3xkEm--/c_scale,f_auto,fl_progressive,q_80,w_800/jsprifdd1gmfy7e7nola.jpg").into(imageView);
        }

        public void onClick(View view){
            String a = accommodations.get(getAdapterPosition());
            AccommodationFragment accommodation = new AccommodationFragment().initializeData(a);
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, accommodation).addToBackStack(null).commit();
        }

    }
}
