package me.moon.market.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.dao.UserRepository;
import me.moon.market.domain.user.dto.UserSaveRequest;
import me.moon.market.domain.user.entity.User;
import me.moon.market.domain.user.exception.EmailDuplicateException;
import me.moon.market.domain.user.exception.PhoneDuplicateException;
import me.moon.market.global.error.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;

    public User doSignUp(UserSaveRequest dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new EmailDuplicateException(dto.getEmail(), ErrorCode.EMAIL_DUPLICATED);
        }

        if(userRepository.existsByPhone(dto.getPhone())){
            throw new PhoneDuplicateException(dto.getPhone(), ErrorCode.PHONE_DUPLICATED);
        }

        return userRepository.save(dto.toEntity());
    }
}
