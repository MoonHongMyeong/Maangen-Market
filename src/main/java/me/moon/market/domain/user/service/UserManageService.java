package me.moon.market.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.dao.UserRepository;
import me.moon.market.domain.user.dto.LoginUserRequest;
import me.moon.market.domain.user.dto.UserSaveRequest;
import me.moon.market.domain.user.entity.User;
import me.moon.market.domain.user.exception.EmailDuplicateException;
import me.moon.market.domain.user.exception.LoginWrongException;
import me.moon.market.domain.user.exception.NicknameDuplicateException;
import me.moon.market.domain.user.exception.PhoneDuplicateException;
import me.moon.market.global.dto.SessionUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserManageService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFindService findService;


    public User doSignUp(UserSaveRequest dto){

        if(userRepository.existsByEmail(dto.getEmail())) throw new EmailDuplicateException(dto.getEmail());
        if(userRepository.existsByPhone(dto.getPhone())) throw new PhoneDuplicateException(dto.getPhone());
        if(userRepository.existsByNickname(dto.getNickname())) throw new NicknameDuplicateException(dto.getNickname());

        dto.encryptPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(dto.toEntity());
    }

    public SessionUser findUserByLoginRequest(LoginUserRequest dto) {
        User user = findService.findByEmail(dto.getEmail());
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new LoginWrongException("");
        }
        return new SessionUser(user);
    }
}
