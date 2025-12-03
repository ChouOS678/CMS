package com.cms.service;

import com.cms.entity.News;
import java.util.List;

public interface NewsService {
    News createNews(News news);

    List<News> getAllNews();

    News getNewsById(Long id);

    News updateNews(News news);

    void deleteNews(Long id);
}
