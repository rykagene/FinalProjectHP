package com.example.finalprojecthp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class main_books extends AppCompatActivity {

    TextView TVtitle, TVsmovie, TVrdate, TVrtime, TVbudget, TVboxoffice, TVdistributors, TVrating, TVorder, TVtrailer, TVwiki, TVdirectors, TVscreenwriters, TVproducers, TVeditors, TVmsccomp, TVsummary;
    ImageButton BTNsearch;
    ImageView char_image;
    EditText ETsearch;
    Spinner moviespinner;


    RecyclerView recyclerView;
    BookAdapter adapter2;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_books);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        fetchApiData();
    }

    private void fetchApiData() {
        String url = "https://api.potterdb.com/v1/books/";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // API response received successfully
                generateDataAndSetupRecyclerView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error in fetching the API response
                error.printStackTrace();
                Toast.makeText(main_books.this, "Error fetching API data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    private void generateDataAndSetupRecyclerView(String apiResponse) {
        List<ItemData2> data = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(apiResponse);
            JSONArray jsonArray = jsonResponse.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieObject = jsonArray.getJSONObject(i);
                JSONObject attributesObject = movieObject.getJSONObject("attributes");

                String id = movieObject.getString("id");
                String imageUrl = attributesObject.getString("cover");
                String title = attributesObject.getString("title");
                String release = attributesObject.getString("release_date");
                String author = attributesObject.getString("author");
                String summary = attributesObject.getString("summary");

//                String time = attributesObject.getString("running_time");
//                String rating = attributesObject.getString("rating");
//                String director = attributesObject.getString("directors");
//                String producer = attributesObject.getString("producers");

                ItemData2 itemData2 = new ItemData2(id,imageUrl, title, release, summary, author);
                data.add(itemData2);
            }

            layoutManager = new LinearLayoutManager(this);
            adapter2 = new BookAdapter(this, R.layout.book_list_view, data);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter2);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(main_books.this, "Error parsing API data", Toast.LENGTH_SHORT).show();
        }

        adapter2.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ItemData2 selectedItem = data.get(position);

                Intent intent = new Intent(main_books.this, BookDetailsActivity.class);
                intent.putExtra("bookId", selectedItem.getId());
                intent.putExtra("bookTitle", selectedItem.getTitle());
                intent.putExtra("book_img", selectedItem.getImageUrl());
                intent.putExtra("bookSummary", selectedItem.getSummary());
                intent.putExtra("bookAuthor", selectedItem.getAuthor());
                intent.putExtra("bookRelease", selectedItem.getRelease());
                startActivity(intent);
            }
        });




    }

}




