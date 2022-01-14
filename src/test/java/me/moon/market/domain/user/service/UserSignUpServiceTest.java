package me.moon.market.domain.user.service;


import me.moon.market.config.MockTest;
import me.moon.market.domain.user.dao.UserRepository;
import me.moon.market.domain.user.dto.UserSaveRequest;
import me.moon.market.domain.user.entity.User;
import me.moon.market.domain.user.exception.EmailDuplicateException;
import me.moon.market.domain.user.exception.NicknameDuplicateException;
import me.moon.market.domain.user.exception.PhoneDuplicateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class UserSignUpServiceTest extends MockTest {
    @InjectMocks
    private UserSignUpService signUpService;

    @Mock
    private UserRepository userRepository;

    private static User user;
    private static UserSaveRequest dto;

    @BeforeAll
    public static void setup(){
        user = User.builder()
                .email("testEmail@testHost.com")
                .password("@testUser1234")
                .name("TestUSER")
                .phone("01024704368")
                .nickname("TestUSER")
                .build();

        dto = UserSaveRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .build();
    }

    @Test
    @DisplayName("회원가입에 성공한다.")
    public void doSignUpTest(){
        //given
        given(userRepository.save(any(User.class))).willReturn(dto.toEntity());

        //when
        final User signUpUser = signUpService.doSignUp(dto);

        //then
        assertThat(signUpUser).isNotNull();
        assertThat(signUpUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(signUpUser.getPhone()).isEqualTo(user.getPhone());
        assertThat(signUpUser.getName()).isEqualTo(user.getName());
        assertThat(signUpUser.getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    @DisplayName("회원가입 기능 이메일 중복시 예외처리")
    public void duplicateEmailWhenSignUp(){
        //given
        when(userRepository.existsByEmail(any(String.class))).thenReturn(true);

        //then
        assertThrows(EmailDuplicateException.class, () -> signUpService.doSignUp(dto));
    }

    @Test
    @DisplayName("회원가입 기능 전화번호 중복시 예외처리")
    public void duplicatePhoneWhenSignUp(){
        //given
        when(userRepository.existsByPhone(any(String.class))).thenReturn(true);

        //then
        assertThrows(PhoneDuplicateException.class, () -> signUpService.doSignUp(dto));
    }

    @Test
    @DisplayName("회원가입 기능 닉네임 중복시 예외처리")
    public void duplicateNicknameWhenSighUp(){
        //given
        when(userRepository.existsByNickname(any(String.class))).thenReturn(true);

        //then
        assertThrows(NicknameDuplicateException.class, () -> signUpService.doSignUp(dto));
    }
}
