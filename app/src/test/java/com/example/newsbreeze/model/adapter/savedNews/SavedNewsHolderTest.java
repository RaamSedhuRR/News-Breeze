package com.example.newsbreeze.model.adapter.savedNews;

import static org.mockito.Mockito.mock;

import com.example.newsbreeze.databinding.SavedNewsItemBinding;
import com.example.newsbreeze.model.adapter.news.NewsViewHolder;
import com.example.newsbreeze.view.SavedNews.SavedNewsFragment;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SavedNewsHolderTest extends TestCase {

    SavedNewsHolder savedNewsHolder;

    SavedNewsItemBinding binding;

    SavedNewsAdapter.OnNewsListener onNewsListener;

    @Before
    public void setUp() {
        binding = mock(SavedNewsItemBinding.class);
        onNewsListener = mock(SavedNewsFragment.class);
        savedNewsHolder = new SavedNewsHolder(
                binding.getRoot()
        );
        savedNewsHolder = mock(SavedNewsHolder.class);
        savedNewsHolder.binding.authorName.setText("Raam Sedhu");
        savedNewsHolder.binding.newsHashtag.setText("#AndroidDev");
        savedNewsHolder.binding.newsDate.setText("20/08/2022");
        savedNewsHolder.binding.newsTitle.setText("Open To Work");

    }

    @Test
    public void testNumberOfVariables(){
        assertEquals(4, NewsViewHolder.class.getDeclaredFields().length);
    }

    @Test
    public void testNumberOfConstructor(){
        assertEquals(1,NewsViewHolder.class.getConstructors().length);
    }

    @Test
    public void testNumberOfMethod(){
        assertEquals(4,NewsViewHolder.class.getDeclaredMethods().length);
    }
}