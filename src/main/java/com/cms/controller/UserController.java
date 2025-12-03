package com.cms.controller;

import com.cms.common.JwtUtil;
import com.cms.common.Result;
import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        try {
            User registered = userService.register(user);
            registered.setPassword(null); // Don't return password
            return Result.success(registered);
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");
            String token = userService.login(username, password);

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(401, e.getMessage());
        }
    }

    @GetMapping("/me")
    public Result<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = jwtUtil.getUserIdFromToken(token);
            User user = userService.getCurrentUser(userId);
            user.setPassword(null);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(401, "Unauthorized");
        }
    }

    @PutMapping("/me")
    public Result<User> updateCurrentUser(@RequestHeader("Authorization") String authHeader, @RequestBody User user) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = jwtUtil.getUserIdFromToken(token);
            user.setId(userId);
            User updated = userService.updateCurrentUser(user);
            updated.setPassword(null);
            return Result.success(updated);
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public Result<Void> changePassword(@RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, String> passwords) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = jwtUtil.getUserIdFromToken(token);
            String currentPassword = passwords.get("currentPassword");
            String newPassword = passwords.get("newPassword");
            userService.changePassword(userId, currentPassword, newPassword);
            return Result.success();
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @GetMapping
    public Result<List<User>> getAllUsers() {
        // Should check for SUPER_ADMIN role
        List<User> users = userService.getAllUsers();
        users.forEach(u -> u.setPassword(null));
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setPassword(null);
        }
        return Result.success(user);
    }
}
