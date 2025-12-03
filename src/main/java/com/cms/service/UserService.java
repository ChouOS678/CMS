package com.cms.service;

import com.cms.entity.User;
import java.util.List;

public interface UserService {
    User register(User user);

    String login(String username, String password);

    User getCurrentUser(Long userId);

    User updateCurrentUser(User user);

    void changePassword(Long userId, String currentPassword, String newPassword);

    List<User> getAllUsers();

    User getUserById(Long id);
}
