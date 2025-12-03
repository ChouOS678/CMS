package com.cms.controller;

import com.cms.common.Result;
import com.cms.entity.News;
import com.cms.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping
    public Result<News> createNews(@RequestBody News news) {
        // Should check for admin role
        return Result.success(newsService.createNews(news));
    }

    @GetMapping
    public Result<List<News>> getAllNews() {
        return Result.success(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public Result<News> getNewsById(@PathVariable Long id) {
        return Result.success(newsService.getNewsById(id));
    }

    @PutMapping("/{id}")
    public Result<News> updateNews(@PathVariable Long id, @RequestBody News news) {
        // Should check for admin role
        news.setId(id);
        return Result.success(newsService.updateNews(news));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteNews(@PathVariable Long id) {
        // Should check for admin role
        newsService.deleteNews(id);
        return Result.success();
    }
}
