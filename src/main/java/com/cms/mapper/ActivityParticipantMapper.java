package com.cms.mapper;

import com.cms.entity.ActivityParticipant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityParticipantMapper {
    int insert(ActivityParticipant participant);

    List<ActivityParticipant> selectByActivityId(Long activityId);

    ActivityParticipant selectByActivityIdAndUserId(@Param("activityId") Long activityId, @Param("userId") Long userId);

    int updateStatus(@Param("id") Long id, @Param("status") String status);
}
