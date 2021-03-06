package com.example.pyk.multimedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.User;
import sql.UserController;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;
    private UserController userController;

    private TextView text_name, text_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // get name
        text_name = findViewById(R.id.Name);
        // get password
        text_pwd = findViewById(R.id.password);
        // set dbhelper
        userController = new UserController(activity);
        // set btn
        Button btnlogin = findViewById(R.id.btnLogin);
        Button btnregister = findViewById(R.id.btnLinkToRegisterScreen);
        Button btn_camera = findViewById(R.id.btnFaceLogin);

        btnlogin.setOnClickListener(this);
        btnregister.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                verifyFromSQLite();
                break;
            case R.id.btnFaceLogin:
                // ********************
                // Open Camera and Take Pictures
                // ********************
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
                break;
            case R.id.btnLinkToRegisterScreen:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
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
        int user_id = userController.login(user);
        if (user_id > 0) {
            Intent i = new Intent(activity, MainActivity.class);
            i.putExtra("id", user_id);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(activity, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == 1) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                //transfer to database
            }
        }
    }
}
