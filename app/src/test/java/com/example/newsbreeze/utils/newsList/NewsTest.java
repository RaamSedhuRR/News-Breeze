package com.example.newsbreeze.utils.newsList;


import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class NewsTest extends TestCase {

    @Test
    public void testNumberOfVariables(){
        assertEquals(3,News.class.getDeclaredFields().length);
    }
    @Test
    public void testNumberOfConstructor(){
        assertEquals(1,Articles.class.getConstructors().length);
    }
    @Test
    public void testGetSetFields(){
        News news = new News();
        ArrayList<Articles> articles = new ArrayList<>();
        news.setArticles(articles);
        news.setStatus("Ok");
        news.setTotalResults(931);
        assertEquals(articles,news.getArticles());
        assertEquals("Ok",news.getStatus());
        assertEquals(931,news.getTotalResults());
    }

}