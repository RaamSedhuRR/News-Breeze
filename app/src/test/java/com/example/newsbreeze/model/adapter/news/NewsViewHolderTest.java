package com.example.newsbreeze.model.adapter.news;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class NewsViewHolderTest extends TestCase {

    @Test
    public void testNumberOfVariables(){
        assertEquals(3,NewsViewHolder.class.getDeclaredFields().length);
    }

    @Test
    public void testNumberOfConstructor(){
        assertEquals(1,NewsViewHolder.class.getConstructors().length);
    }

    @Test
    public void testNumberOfMethod(){
        assertEquals(3,NewsViewHolder.class.getDeclaredMethods().length);
    }


}