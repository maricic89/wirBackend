package com.milos.wir.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.milos.wir.exception.ResourceNotFoundException;
import com.milos.wir.model.UserEntity;
import com.milos.wir.repository.UserRepository;
import com.milos.wir.security.CurrentUser;
import com.milos.wir.security.UserPrincipal;

@RestController
public class UserEndpoint {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findByEmail(userPrincipal.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
