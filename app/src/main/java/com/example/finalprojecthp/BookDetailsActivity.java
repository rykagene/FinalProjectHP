package com.example.finalprojecthp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class BookDetailsActivity extends AppCompatActivity {

    TextView tvbookTitle, tvbookAuthor, tvreleaseTextView,tvsummaryTextView;
    ImageView book_img;
    Button viewBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_book_details);

        tvbookTitle = findViewById(R.id.titleTextView);
        tvbookAuthor = findViewById(R.id.detailsTextView);
        tvsummaryTextView = findViewById(R.id.summaryTextView);
        tvreleaseTextView = findViewById(R.id.releaseTextView);
        book_img = findViewById(R.id.book_img);

//        viewBtn = findViewById(R.id.viewBtn);



        // Retrieve the book details from the intent
        String book_imgUrl = getIntent().getStringExtra("book_img");
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String bookSummary = getIntent().getStringExtra("bookSummary");
        String bookRelease = getIntent().getStringExtra("bookRelease");

        // Use Picasso to load the image into the ImageView
        Picasso.get().load(book_imgUrl).into(book_img);

        tvbookTitle.setText(bookTitle);
        tvbookAuthor.setText(bookAuthor);
        tvreleaseTextView.setText(bookRelease);
        tvsummaryTextView.setText(bookSummary);

//        viewBtn.setOnClickListener(v-> {
//
//        });
    }

}
