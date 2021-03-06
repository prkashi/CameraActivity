package com.example.punit.requestpermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
//
    Button button;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            1888);
                    return;
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1888: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent inte = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(inte,1888);
                } else {

                    Log.d("hi", "hi");
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentShown){
            if(requestCode==1888 && resultCode == Activity.RESULT_OK){
                Bitmap photo = (Bitmap) intentShown.getExtras().get("intentShown");
                image.setImageBitmap(photo);
             }
    }
}
