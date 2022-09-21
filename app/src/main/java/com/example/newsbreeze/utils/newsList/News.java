package com.example.newsbreeze.utils.newsList;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class News {
    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public int totalResults;
    @SerializedName("articles")
    public ArrayList<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
