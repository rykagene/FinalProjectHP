package com.example.finalprojecthp;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MovieDetailsActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView detailsTextView;
    private TextView directorTextView;

    public String url = "PbdM1db3JbY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if (getIntent().hasExtra("title")) {
            String title = getIntent().getStringExtra("title");
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No title", Toast.LENGTH_SHORT).show();
        }


//        if (!(pos=="1")){
//            Toast.makeText(this, "nice "+time, Toast.LENGTH_SHORT).show();
//            url = "PbdM1db3JbY";
//        }
//        else{
//            url = "asdsad";
//        }




        WebView webView = findViewById(R.id.youtubeWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());



        String videoUrl = "https://www.youtube.com/embed/" + url;
        String html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);


        titleTextView = findViewById(R.id.titleTextView);
        detailsTextView = findViewById(R.id.detailsTextView);
        directorTextView = findViewById(R.id.directorTextView);

        // Retrieve the movie ID from the Intent
        String movieId = getIntent().getStringExtra("movieId");


        // Update the TextViews with the retrieved information
//        titleTextView.setText(title);
//        detailsTextView.setText(details);
//        directorTextView.setText(time);


    }
}
