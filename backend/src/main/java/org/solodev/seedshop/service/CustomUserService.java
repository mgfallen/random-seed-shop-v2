package org.solodev.seedshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.solodev.seedshop.model.CustomUser;
import org.solodev.seedshop.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService {

    private final CustomUserRepository userRepository;

    @Autowired
    public CustomUserService(CustomUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomUser getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }
}
