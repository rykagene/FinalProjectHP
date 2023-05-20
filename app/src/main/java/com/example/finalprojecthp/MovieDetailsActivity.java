package com.example.finalprojecthp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


public class MovieDetailsActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView detailsTextView;
    private TextView directorTextView;
    private AppCompatButton BackBTN, ViewWIKI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        WebView webView = findViewById(R.id.youtubeWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        String movTrailer = getIntent().getStringExtra("movTrailer");
        String movTrailerURL = movTrailer.substring(32);
        String videoUrl = "https://www.youtube.com/embed/" + movTrailerURL;

        String html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);


        titleTextView = findViewById(R.id.titleTextView);
        detailsTextView = findViewById(R.id.detailsTextView);
        directorTextView = findViewById(R.id.directorTextView);
        BackBTN = findViewById(R.id.BackBTN);
        ViewWIKI = findViewById(R.id.ViewWIKI);

        // Retrieve data from Main Movies Class
        String movTitle = getIntent().getStringExtra("movTitle");
        String movRelease = getIntent().getStringExtra("movRelease");
        String movTime = getIntent().getStringExtra("movTime");
        String movRating = getIntent().getStringExtra("movRating");
        String movDirector = getIntent().getStringExtra("movDirector");
        String movProducer = getIntent().getStringExtra("movProducer");
        String movBudget = getIntent().getStringExtra("movBudget");
        String movBoxOffice = getIntent().getStringExtra("movBoxOffice");
        String movWiki = getIntent().getStringExtra("movWiki");

        if (movDirector.contains("[") || (movProducer.contains("["))){
            movDirector.replace("[", "");
            movDirector.replace("]", "");
            movProducer.replace("[", "");
            movProducer.replace("]", "");

            // Update the TextViews with the retrieved information
            titleTextView.setText(movTitle);
            detailsTextView.setText(
                    "Rating: " + movRating +"\n" +
                            "Date Released: " + movRelease + "\n" +
                            "Running Time: " + movTime + "\n" +
                            "Budget: " + movBudget + "\n" +
                            "Box Office: " + movBoxOffice + "\n"
            );
            directorTextView.setText(
                    "Directed by: " + movDirector + "\n" +
                            "Produced by: " + movProducer
            );
        }

        BackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ViewWIKI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(movWiki);
                Intent wiki = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(wiki);
            }
        });
    }

}