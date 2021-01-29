package com.example.android.coronavirus_outbreaknews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    interface ListItemClickListener {
        void onListItemClick(String url);
    }

    private List<NewsItem> mNewsItems;
    private final ListItemClickListener mItemOnClickListener;

    public NewsAdapter(List<NewsItem> newsItems, ListItemClickListener itemOnClickListener) {
        mNewsItems = newsItems;
        mItemOnClickListener = itemOnClickListener;
    }

    public void resetData(List<NewsItem> newsItems) {
        mNewsItems = newsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.bind(mNewsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView titleView;
        private final TextView sectionView;
        private final TextView authorsView;
        private final TextView publishedView;

        private String url;

        public NewsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.title);
            sectionView = itemView.findViewById(R.id.section);
            authorsView = itemView.findViewById(R.id.authors);
            publishedView = itemView.findViewById(R.id.published);
            itemView.setOnClickListener(this);
        }

        public void bind(NewsItem newsItem) {
            titleView.setText(newsItem.getTitle());
            sectionView.setText(newsItem.getSection());
            authorsView.setText(newsItem.getAuthors());
            publishedView.setText(newsItem.getDate());

            url = newsItem.getUrl();
        }

        @Override
        public void onClick(View v) {
            mItemOnClickListener.onListItemClick(url);
        }
    }
}
