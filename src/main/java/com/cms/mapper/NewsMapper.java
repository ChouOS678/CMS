package com.cms.mapper;

import com.cms.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    int insert(News news);

    News selectById(Long id);

    List<News> selectAll();

    int update(News news);

    int deleteById(Long id);
}
