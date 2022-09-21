package com.example.newsbreeze.utils.newsList;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SourceTest extends TestCase {

    @Test
    public void testNumberOfVariables(){
        assertEquals(2,Source.class.getDeclaredFields().length);
    }

    @Test
    public void testNumberOfConstructor(){
        assertEquals(1,Articles.class.getConstructors().length);
    }

    @Test
    public void testGetSetFields(){
        Source source = new Source();
        source.setId("one");
        source.setName("one");
        assertEquals("one",source.getId());
        assertEquals("one",source.getName());
    }
}