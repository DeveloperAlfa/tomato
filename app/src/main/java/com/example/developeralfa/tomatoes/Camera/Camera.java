package com.example.developeralfa.tomatoes.Camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.developeralfa.tomatoes.Camera.Retrofit.Feature;
import com.example.developeralfa.tomatoes.Camera.Retrofit.Image;
import com.example.developeralfa.tomatoes.Camera.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.Camera.Retrofit.Req;
import com.example.developeralfa.tomatoes.Camera.Retrofit.Request;
import com.example.developeralfa.tomatoes.Camera.Retrofit.Res;
import com.example.developeralfa.tomatoes.Camera.Retrofit.Response;
import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.Search.Search;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class Camera extends AppCompatActivity {

    String s;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mImageView = findViewById(R.id.mImageView);
        dispatchTakePictureIntent();
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            s = Base64.encodeToString(byteArray,Base64.DEFAULT);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vision.googleapis.com/v1/").addConverterFactory(GsonConverterFactory.create()).build();
            RFinterface rFinterface = retrofit.create(RFinterface.class);
            ArrayList<Request> requests = new ArrayList<>();
            ArrayList<Feature> features = new ArrayList<>();
            features.add(new Feature("TEXT_DETECTION"));
            requests.add(new Request(new Image(s),features));
            Call<Res> responseCall = rFinterface.getImagedetails("AIzaSyAZbXIwXHuzOhaL2XQLYisjobfkYF_qJPo",new Req(requests));
            responseCall.enqueue(new Callback<Res>() {
                String string;
                @Override
                public void onResponse(Call<Res> call, retrofit2.Response<Res> response) {

                     if(response.body().getResponses().get(0).getTextAnnotations()==null) {
                        string = "nothing detected";
                     }
                     else {
                         string = response.body().getResponses().get(0).getTextAnnotations().get(0).getDescription();
                     }
                     Intent intent = new Intent(Camera.this, Search.class);
                     intent.putExtra("q",string.replace("\n"," "));
                     startActivity(intent);
                }

                @Override
                public void onFailure(Call<Res> call, Throwable t) {

                }
            });
        }
    }
}
