package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        List<ItemData> data = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(apiResponse);
            JSONArray jsonArray = jsonResponse.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieObject = jsonArray.getJSONObject(i);
                JSONObject attributesObject = movieObject.getJSONObject("attributes");

                String id = movieObject.getString("id");
                String imageUrl = attributesObject.getString("poster");
                String title = attributesObject.getString("title");
                String release = attributesObject.getString("release_date");
                String time = attributesObject.getString("running_time");
                String rating = attributesObject.getString("rating");
                String director = attributesObject.getString("directors");
                String producer = attributesObject.getString("producers");

                ItemData itemData = new ItemData(id,imageUrl, title, release, time, rating, director, producer);
                data.add(itemData);
            }

            adapter = new CustomAdapter(this, R.layout.list_item_view, data);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(main_books.this, "Error parsing API data", Toast.LENGTH_SHORT).show();
        }
    }
}