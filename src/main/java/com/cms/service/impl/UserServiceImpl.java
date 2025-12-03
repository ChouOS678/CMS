package com.cms.service.impl;

import com.cms.entity.User;
import com.cms.mapper.UserMapper;
import com.cms.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Override
    public User register(User user) {
        // Check if username exists
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // In a real app, password should be hashed
        userMapper.insert(user);
        return user;
    }

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate JWT
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public User getCurrentUser(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User updateCurrentUser(User user) {
        user.setUpdateTime(new Date());
        userMapper.update(user);
        return userMapper.selectById(user.getId());
    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        if (!user.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Invalid current password");
        }
        userMapper.updatePassword(userId, newPassword);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
}
