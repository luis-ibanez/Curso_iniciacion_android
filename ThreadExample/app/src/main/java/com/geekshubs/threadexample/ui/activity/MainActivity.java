package com.geekshubs.threadexample.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.geekshubs.threadexample.R;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String WOMBAT_IMAGE = "http://i.livescience.com/images/i/000/019/567/original/wombat-110831.jpg";
    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.image = (ImageView) findViewById(R.id.main_image);
        this.button = (Button) findViewById(R.id.main_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageAsyncTask();
            }
        });
    }

    public void loadImageBad(){
        Bitmap b = loadImageFromNetwork(WOMBAT_IMAGE);
        if (b != null) {
            image.setImageBitmap(b);
        }
    }

    public void loadImage() {
        new Thread(new Runnable() {
            public void run() {
                Bitmap b = loadImageFromNetwork(WOMBAT_IMAGE);
                if (b != null) {
                    image.setImageBitmap(b);
                }
            }
        }).start();
    }
    public void loadImageBetter() {
        new Thread(new Runnable() {
            public void run() {
                final Bitmap bitmap =
                        loadImageFromNetwork(WOMBAT_IMAGE);
                image.post(new Runnable() {
                    public void run() {
                        image.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    public void loadImageAsyncTask(){
        new DownloadImageTask().execute(WOMBAT_IMAGE);
    }

    public Bitmap loadImageFromNetwork(String imageUrl) {
        Bitmap bmp = null;
        try {
            URL url = new URL(imageUrl);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            return loadImageFromNetwork(strings[0]);
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }
}
