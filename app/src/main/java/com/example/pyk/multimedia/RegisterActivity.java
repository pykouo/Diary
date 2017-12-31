package com.example.pyk.multimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URI;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        ********************
//        go back to login
//        ********************
        Button button = (Button) findViewById(R.id.btnLinkToLoginScreen);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

//        ********************
//        Camera Intent (default)
//        ********************


//        ********************
//        Open Camera and Take Pictures
//        ********************
        Button facecam = (Button) findViewById(R.id.btn_camera);

        facecam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1887);

            }


        });


//        ********************
//        Click Register
//        ********************
        Button btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //get name
                TextView text_name = (TextView) findViewById(R.id.Name);
                String name = text_name.getText().toString();
                //get pwd
                TextView text_pwd = (TextView) findViewById(R.id.password);
                String pwd = text_pwd.getText().toString();
                //get face image
                ImageView face = (ImageView) findViewById(R.id.img_face);

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == 1887) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
//                Bitmap resized_image = Bitmap.createScaledBitmap(image, 180, 120, true);
                ImageView imageview = (ImageView) findViewById(R.id.img_face); //sets imageview as the bitmap
                imageview.setImageBitmap(image);
            }
        }
    }
}
