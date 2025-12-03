package com.cms.service.impl;

import com.cms.entity.Club;
import com.cms.entity.ClubMember;
import com.cms.mapper.ClubMapper;
import com.cms.mapper.ClubMemberMapper;
import com.cms.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private ClubMemberMapper clubMemberMapper;

    @Override
    public Club createClub(Club club) {
        club.setCreateTime(new Date());
        club.setUpdateTime(new Date());
        club.setStatus("PENDING"); // Default status
        clubMapper.insert(club);
        return club;
    }

    @Override
    public List<Club> getAllClubs() {
        return clubMapper.selectAll();
    }

    @Override
    public Club getClubById(Long id) {
        return clubMapper.selectById(id);
    }

    @Override
    public Club updateClub(Club club) {
        club.setUpdateTime(new Date());
        clubMapper.update(club);
        return clubMapper.selectById(club.getId());
    }

    @Override
    public void deleteClub(Long id) {
        clubMapper.deleteById(id);
    }

    @Override
    public void joinClub(Long userId, Long clubId, String reason) {
        ClubMember existing = clubMemberMapper.selectByClubIdAndUserId(clubId, userId);
        if (existing != null) {
            throw new RuntimeException("Already applied or joined");
        }
        ClubMember member = new ClubMember();
        member.setClubId(clubId);
        member.setUserId(userId);
        member.setRole("MEMBER");
        member.setStatus("PENDING");
        member.setJoinTime(new Date());
        clubMemberMapper.insert(member);
    }

    @Override
    public List<ClubMember> getClubApplications(Long clubId) {
        // In a real app, filter by status=PENDING
        return clubMemberMapper.selectByClubId(clubId);
    }

    @Override
    public void reviewApplication(Long applicationId, boolean approved, String message) {
        String status = approved ? "APPROVED" : "REJECTED";
        clubMemberMapper.updateStatus(applicationId, status);
    }

    @Override
    public List<ClubMember> getClubMembers(Long clubId) {
        return clubMemberMapper.selectByClubId(clubId);
    }

    @Override
    public void leaveClub(Long userId, Long clubId) {
        clubMemberMapper.deleteByClubIdAndUserId(clubId, userId);
    }

    @Override
    public void removeMember(Long clubId, Long userId) {
        clubMemberMapper.deleteByClubIdAndUserId(clubId, userId);
    }
}
