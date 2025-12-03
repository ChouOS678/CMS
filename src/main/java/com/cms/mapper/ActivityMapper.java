package com.cms.mapper;

import com.cms.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    int insert(Activity activity);

    Activity selectById(Long id);

    List<Activity> selectAll();

    List<Activity> selectByClubId(Long clubId);

    int update(Activity activity);

    int deleteById(Long id);
}
