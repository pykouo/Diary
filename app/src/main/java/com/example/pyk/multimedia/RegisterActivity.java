package com.example.pyk.multimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.User;
import sql.DBHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;
    private DBHelper dbHelper;

    private TextView text_name, text_pwd;
    private ImageView face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button backLogin = findViewById(R.id.btnLinkToLoginScreen);
        Button facecam = findViewById(R.id.btn_camera);
        Button btn_register = findViewById(R.id.btn_register);
        text_name = findViewById(R.id.name);
        text_pwd = findViewById(R.id.password);
        face = findViewById(R.id.img_face);
        // set dbhelper
        dbHelper = new DBHelper(activity);
        backLogin.setOnClickListener(this);
        facecam.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                //  ********************
                //  Open Camera and Take Pictures
                //  ********************
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1887);
                break;
            case R.id.btn_register:
                //  ********************
                //  Click Register
                //  ********************
                verifyFromSQLite();
                break;
            case R.id.btnLinkToLoginScreen:
                //  ********************
                //  go back to login
                //  ********************
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    private void verifyFromSQLite() {

        if (text_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Name is Empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (text_pwd.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Password is Empty", Toast.LENGTH_LONG).show();
            return;
        }
        User user = new User();
        user.setName(text_name.getText().toString());
        user.setPassword(text_pwd.getText().toString());
        dbHelper.register(user);
        Intent i = new Intent(activity, LoginActivity.class);
        startActivity(i);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == 1887) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
//                Bitmap resized_image = Bitmap.createScaledBitmap(image, 180, 120, true);
                face.setImageBitmap(image);
            }
        }
    }
}
