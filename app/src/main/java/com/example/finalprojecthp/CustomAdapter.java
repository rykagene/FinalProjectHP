package com.example.finalprojecthp;

import android.content.Context;
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
        public List<ItemData> data;
        Context context;
        int layout;

        static OnItemClickListener listener;

        public CustomAdapter(Context context, int layout, List<ItemData> data) {
                this.context = context;
                this.layout = layout;
                this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View rowItem = inflater.inflate(layout, parent, false);
                return new ViewHolder(rowItem);

        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
                ItemData item = data.get(position);
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

        }

        @Override
        public int getItemCount() {
                return this.data.size();
        }

        public interface OnItemClickListener{
                void onItemClick(int position);

        }
        public void setOnItemClickListener(OnItemClickListener listenerActivity) {
                listener = listenerActivity;
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
                        if (listener != null){
                                int position = getAdapterPosition();
                                if (position != RecyclerView.NO_POSITION){
                                        listener.onItemClick(position);
                                }
                        }


                }
        }

}
