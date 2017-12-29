package com.example.pyk.multimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get name
                TextView text_name = (TextView) findViewById(R.id.Name);
                String name = text_name.getText().toString();

                // get password
                TextView text_pwd = (TextView) findViewById(R.id.password);
                String pwd = text_pwd.getText().toString();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

//        ********************
//        Open Camera and Take Pictures
//        ********************
        Button btn_camera = (Button) findViewById(R.id.btnFaceLogin);

        btn_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new      Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == 1) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                //transfer to database
            }
        }
    }
}
