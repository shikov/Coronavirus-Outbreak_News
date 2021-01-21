package com.example.android.coronavirus_outbreaknews;

import java.util.Date;

public class NewsItem {

    private final String mTitle;
    private final String mSection;
    private final String mAuthors;
    private final String mDate;
    private final String mUrl;

    public NewsItem(String title, String section, String authors, String date, String url) {
        mTitle = title;
        mSection = section;
        mAuthors = authors;
        mDate = date;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
