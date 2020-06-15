package com.carrot.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Article> newslist;
    OnNewsClickListener onNewsClickListener;
    Context context;
    public RecyclerViewAdapter(List<Article> list , Context context , OnNewsClickListener onNewsClickListener){
        this.newslist = list;
        this.context = context;
        this.onNewsClickListener = onNewsClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.news_card , parent , false);
        return new ViewHolder(view , onNewsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article obj = newslist.get(position);
        holder.title.setText(obj.getTitle());
        Glide.with(context).load(obj.getUrlToImage()).into(holder.imageView);
        holder.author.setText(obj.getAurthor());
    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView author;
        ImageView imageView;
        OnNewsClickListener onNewsClickListener;

        public ViewHolder(@NonNull View itemView , OnNewsClickListener onNewsClickListener) {
            super(itemView);
           title = itemView.findViewById(R.id.news_title);
           author = itemView.findViewById(R.id.author_news);
           imageView = itemView.findViewById(R.id.news_image);
           this.onNewsClickListener = onNewsClickListener;
           itemView.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View v) {
            onNewsClickListener.onNewsClick(getAdapterPosition());
        }
    }
    public interface OnNewsClickListener{
        void onNewsClick(int pos);
    }
}
