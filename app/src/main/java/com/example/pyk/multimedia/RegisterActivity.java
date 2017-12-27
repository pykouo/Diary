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
//        ImageView face = (ImageView) findViewById(R.id.img_face);
        Button facecam = (Button) findViewById(R.id.btn_camera);

        facecam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //利用intent去開啟android本身的照相介面
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //設定圖片的儲存位置，以及檔名
                File tmpFile = new File(Environment.getExternalStorageDirectory(), "image.jpg");
                Uri outputFileUri = Uri.fromFile(tmpFile);
                /*
                 * 把上述的設定put進去！然後startActivityForResult,
                 * 記住，因為是有ForResult，所以在本身自己的acitivy裡面等等要複寫onActivityResult
                 * 稍後再說明onActivityResult
                 */
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, 0);

            }


        });


//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (resultCode == RESULT_OK) {
//                Bitmap bmp = BitmapFactory.decodeFile(outputFileUri.getPath()); //利用BitmapFactory去取得剛剛拍照的圖像
//                ImageView face = (ImageView)findViewById(R.id.img_face);
//                face.setImageBitmap(bmp);
//            }
//        }


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
        if (requestCode == 0) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.img_face); //sets imageview as the bitmap
            imageview.setImageBitmap(image);
        }
    }
}
