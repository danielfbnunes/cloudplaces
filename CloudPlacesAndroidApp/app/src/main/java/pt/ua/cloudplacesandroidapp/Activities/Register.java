package pt.ua.cloudplacesandroidapp.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ua.cloudplacesandroidapp.R;

public class Register extends AppCompatActivity {
    // UI references.
    private static final int SELECT_PHOTO = 100;
    private ImageView chooseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button regist = (Button) findViewById(R.id.register_btn_confirm);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });

        chooseImage = (ImageView) findViewById(R.id.imageView2);
        Button pickImage = (Button) findViewById(R.id.pickImage);
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage(v);
            }
        });
        // Don't show keyboard on beginning
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void ChooseImage(View v){
        openGallery();
    }

    private void openGallery(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    if(selectedImage !=null){
                        chooseImage.setImageURI(selectedImage);
                    }
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void attemptRegister() {
        //TODO: validate fields
        startActivity(new Intent(Register.this, Welcome.class));
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
    }
}
