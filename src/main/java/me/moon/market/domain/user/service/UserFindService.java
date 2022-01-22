package me.moon.market.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.dao.UserRepository;
import me.moon.market.domain.user.entity.User;
import me.moon.market.global.dto.SessionUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;

    public User findBySessionUser(SessionUser user) {
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getEmail()));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
