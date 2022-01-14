package me.moon.market.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.dao.UserRepository;
import me.moon.market.domain.user.dto.UserSaveRequest;
import me.moon.market.domain.user.entity.User;
import me.moon.market.domain.user.exception.EmailDuplicateException;
import me.moon.market.domain.user.exception.NicknameDuplicateException;
import me.moon.market.domain.user.exception.PhoneDuplicateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;

    public User doSignUp(UserSaveRequest dto){

        if(userRepository.existsByEmail(dto.getEmail())) throw new EmailDuplicateException(dto.getEmail());
        if(userRepository.existsByPhone(dto.getPhone())) throw new PhoneDuplicateException(dto.getPhone());
        if(userRepository.existsByNickname(dto.getNickname())) throw new NicknameDuplicateException(dto.getNickname());

        return userRepository.save(dto.toEntity());
    }

}
