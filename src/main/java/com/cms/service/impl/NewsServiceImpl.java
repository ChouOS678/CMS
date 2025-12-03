package com.cms.service.impl;

import com.cms.entity.News;
import com.cms.mapper.NewsMapper;
import com.cms.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public News createNews(News news) {
        news.setPublishTime(new Date());
        newsMapper.insert(news);
        return news;
    }

    @Override
    public List<News> getAllNews() {
        return newsMapper.selectAll();
    }

    @Override
    public News getNewsById(Long id) {
        return newsMapper.selectById(id);
    }

    @Override
    public News updateNews(News news) {
        newsMapper.update(news);
        return newsMapper.selectById(news.getId());
    }

    @Override
    public void deleteNews(Long id) {
        newsMapper.deleteById(id);
    }
}
