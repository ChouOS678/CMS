package com.cms.mapper;

import com.cms.entity.Club;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    int insert(Club club);

    Club selectById(Long id);

    List<Club> selectAll();

    List<Club> selectByCreatorId(Long creatorId);

    int update(Club club);

    int deleteById(Long id);
}
