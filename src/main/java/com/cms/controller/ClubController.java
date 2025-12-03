package com.cms.controller;

import com.cms.common.JwtUtil;
import com.cms.common.Result;
import com.cms.entity.Club;
import com.cms.entity.ClubMember;
import com.cms.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<Club> createClub(@RequestBody Club club) {
        // Should check for SUPER_ADMIN role
        Club created = clubService.createClub(club);
        return Result.success(created);
    }

    @GetMapping
    public Result<List<Club>> getAllClubs() {
        return Result.success(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public Result<Club> getClubById(@PathVariable Long id) {
        return Result.success(clubService.getClubById(id));
    }

    @PutMapping("/{id}")
    public Result<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
        // Should check for club admin role
        club.setId(id);
        return Result.success(clubService.updateClub(club));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteClub(@PathVariable Long id) {
        // Should check for SUPER_ADMIN role
        clubService.deleteClub(id);
        return Result.success();
    }

    @PostMapping("/{id}/join")
    public Result<Void> joinClub(@PathVariable Long id,
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, String> request) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = jwtUtil.getUserIdFromToken(token);
            String reason = request.get("reason");
            clubService.joinClub(userId, id, reason);
            return Result.success();
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @GetMapping("/{id}/applications")
    public Result<List<ClubMember>> getApplications(@PathVariable Long id) {
        // Should check for club admin role
        return Result.success(clubService.getClubApplications(id));
    }

    @PostMapping("/applications/{appId}/review")
    public Result<Void> reviewApplication(@PathVariable Long appId,
            @RequestBody Map<String, Object> review) {
        // Should check for club admin role
        boolean approved = (Boolean) review.get("approved");
        String message = (String) review.get("message");
        clubService.reviewApplication(appId, approved, message);
        return Result.success();
    }

    @GetMapping("/{id}/members")
    public Result<List<ClubMember>> getMembers(@PathVariable Long id) {
        return Result.success(clubService.getClubMembers(id));
    }

    @PostMapping("/{id}/leave")
    public Result<Void> leaveClub(@PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = jwtUtil.getUserIdFromToken(token);
            clubService.leaveClub(userId, id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @DeleteMapping("/{clubId}/members/{userId}")
    public Result<Void> removeMember(@PathVariable Long clubId, @PathVariable Long userId) {
        // Should check for club admin role
        clubService.removeMember(clubId, userId);
        return Result.success();
    }
}
