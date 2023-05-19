package com.example.finalprojecthp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    public List<ItemData2> data;
    Context context;
    int layout;

    static OnItemClickListener listener;

    public BookAdapter(Context context, int layout, List<ItemData2> data) {
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

    //setText()
    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        ItemData2 item = data.get(position);
        holder.textView.setText(item.getTitle());

        String release = item.getRelease();
        holder.textView2.setText(release.substring(0, 4));;

        holder.textView3.setText(item.getAuthor());

        holder.textView4.setText(item.getSummary());


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

        private final TextView textView;
        private final TextView textView2;
        private final TextView textView3;
        private final TextView textView4;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.imageView = view.findViewById(R.id.book_image);
            this.textView = view.findViewById(R.id.book_title);
            this.textView2 = view.findViewById(R.id.book_release);
            this.textView3 = view.findViewById(R.id.book_author);
            this.textView4 = view.findViewById(R.id.book_summary);

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
