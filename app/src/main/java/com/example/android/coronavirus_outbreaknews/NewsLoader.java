package com.example.android.coronavirus_outbreaknews;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {
    private String mUrlString;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrlString = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<NewsItem> loadInBackground() {
        if (mUrlString == null) {
            return null;
        }
        return QueryUtils.fetchNewsData(mUrlString);
    }
}
