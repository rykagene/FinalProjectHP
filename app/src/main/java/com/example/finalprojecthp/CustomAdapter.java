package com.example.finalprojecthp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        private List<ItemData> data;

        public CustomAdapter(List<ItemData> data) {
                this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
                return new ViewHolder(rowItem);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
                ItemData item = this.data.get(position);
                holder.textView.setText(item.getTitle());

                String release = item.getRelease();
                String first4Letters = release.substring(0, 4);
                holder.textView2.setText(first4Letters);

                String time = item.getTime();
                String first7Letters = time.substring(0, 7);
                holder.textView3.setText(first7Letters);


                // Use an image loading library like Picasso or Glide to load the image from the URL
                // into an ImageView in your list_item_view layout. Refer to their documentation for usage.
                // Example with Picasso:
                 Picasso.get().load(item.getImageUrl()).into(holder.imageView);

                // Set onClickListener for the item
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                // Start MovieDetailsActivity and pass the movie ID
                                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                                intent.putExtra("movieId", item.getId());
                                view.getContext().startActivity(intent);
                        }
                });
        }

        @Override
        public int getItemCount() {
                return this.data.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

                private final TextView textView, textView2, textView3;
                private final ImageView imageView;

                public ViewHolder(View view) {
                        super(view);
                        view.setOnClickListener(this);
                        this.textView = view.findViewById(R.id.movie_title);
                        this.textView2 = view.findViewById(R.id.movie_details);
                        this.textView3 = view.findViewById(R.id.movie_time);
                        this.imageView = view.findViewById(R.id.movie_image);
                }

                @Override
                public void onClick(View view) {
                        // Get the position of the clicked item.


                        // Get the data for the clicked item.
                        String title = textView.getText().toString();
                        String details = textView2.getText().toString();
                        String time = textView3.getText().toString();
                        int pos = getLayoutPosition();


                        // Create an intent to send the data to another activity.
                        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("details", details);
                        intent.putExtra("time", time);
                        intent.putExtra(String.valueOf(pos), pos);




                        // Start the activity.
                        view.getContext().startActivity(intent);


                }
        }

}
