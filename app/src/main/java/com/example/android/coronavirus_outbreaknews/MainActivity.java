package com.example.android.coronavirus_outbreaknews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ListItemClickListener
        , LoaderManager.LoaderCallbacks<List<NewsItem>> {

    private static final String NEWS_REQUEST_URL =
            "https://content.guardianapis.com/search?tag=world%2Fcoronavirus-outbreak&show-tags=contributor&api-key=test";
    private static final int NEWS_LOADER_ID = 1;

    private TextView mEmptyStateTextView;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mNewsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        mLoadingIndicator = findViewById(R.id.loading_indicator);
        mNewsListView = findViewById(R.id.news_list);
        mNewsListView.setLayoutManager(new LinearLayoutManager(this));
        mNewsListView.setHasFixedSize(true);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            mLoadingIndicator.setVisibility(View.VISIBLE);
            LoaderManager.getInstance(this).initLoader(NEWS_LOADER_ID, null, this);
        }
        else {
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onListItemClick(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    @NonNull
    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsItem>> loader, List<NewsItem> data) {
        mLoadingIndicator.setVisibility(View.GONE);
        if (data == null || data.isEmpty()) {
            mNewsListView.setAdapter(new NewsAdapter(new ArrayList<>(), this));
            mEmptyStateTextView.setText(R.string.no_news);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
        else {
            mNewsListView.setAdapter(new NewsAdapter(data, this));
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsItem>> loader) {
        mNewsListView.setAdapter(new NewsAdapter(new ArrayList<>(), this));
    }
}