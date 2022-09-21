package com.example.newsbreeze.utils.newsList;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArticlesTest extends TestCase {
    @Test
    public void testNumberOfVariables(){
        assertEquals(9,Articles.class.getDeclaredFields().length);
    }
    @Test
    public void testNumberOfConstructor(){
      assertEquals(1,Articles.class.getConstructors().length);
    }
    @Test
    public void testGetSetFields(){
    Articles articles = new Articles();
    Source source = new Source();
    articles.setAuthor("Raam Sedhu RR");
    articles.setSource(source);
    articles.setSaved(false);
    articles.setTitle("Title Text");
    articles.setSaved(false);
    articles.setContent("This is a Content");
    articles.setDescription("This is a Description");
    articles.setPublishedAt("20-08-2022");
    articles.setUrl("https://www.tiktok.com/legal/privacy-policy-us?lang=en");
    articles.setUrlToImage("https://www.tiktok.com/legal/privacy-policy-us?lang=en");
    assertEquals("Title Text",articles.getTitle());
    assertEquals("Raam Sedhu RR",articles.getAuthor());
    assertFalse(articles.getSaved());
    assertEquals(source,articles.getSource());
    assertEquals("This is a Content",articles.getContent());
    assertEquals("This is a Description",articles.getDescription());
    assertEquals("20-08-2022",articles.getPublishedAt());
    assertEquals("https://www.tiktok.com/legal/privacy-policy-us?lang=en",
            articles.getUrl());
    assertEquals("https://www.tiktok.com/legal/privacy-policy-us?lang=en",
            articles.getUrlToImage());
    }

}