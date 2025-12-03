package com.cms.mapper;

import com.cms.entity.ClubMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClubMemberMapper {
    int insert(ClubMember clubMember);

    ClubMember selectById(Long id);

    List<ClubMember> selectByClubId(Long clubId);

    List<ClubMember> selectByUserId(Long userId);

    ClubMember selectByClubIdAndUserId(@Param("clubId") Long clubId, @Param("userId") Long userId);

    int updateStatus(@Param("id") Long id, @Param("status") String status);

    int deleteById(Long id);

    int deleteByClubIdAndUserId(@Param("clubId") Long clubId, @Param("userId") Long userId);
}
