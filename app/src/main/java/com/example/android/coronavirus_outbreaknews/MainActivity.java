package com.example.android.coronavirus_outbreaknews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView newsListView = findViewById(R.id.news_list);
        newsListView.setLayoutManager(new LinearLayoutManager(this));
        newsListView.setHasFixedSize(true);

        List<NewsItem> newsItems = QueryUtils.extractNews();

        newsListView.setAdapter(new NewsAdapter(newsItems, this));
    }

    @Override
    public void onListItemClick(String url) {
        // TODO: open url in browser
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
    }
}