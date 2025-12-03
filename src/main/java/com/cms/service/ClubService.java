package com.cms.service;

import com.cms.entity.Club;
import com.cms.entity.ClubMember;
import java.util.List;

public interface ClubService {
    Club createClub(Club club);

    List<Club> getAllClubs();

    Club getClubById(Long id);

    Club updateClub(Club club);

    void deleteClub(Long id);

    void joinClub(Long userId, Long clubId, String reason);

    List<ClubMember> getClubApplications(Long clubId);

    void reviewApplication(Long applicationId, boolean approved, String message);

    List<ClubMember> getClubMembers(Long clubId);

    void leaveClub(Long userId, Long clubId);

    void removeMember(Long clubId, Long userId);
}
