package me.moon.market.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.user.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserSaveRequest {

    @NotBlank
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank
    @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(message ="'-'를 제외한 핸드폰 번호를 정확히 입력해주세요.",
            regexp = "^[0-9].{10,11}$")
    private String phone;

    @NotBlank
    private String nickname;

    @Builder
    public UserSaveRequest(String email, String password, String name, String phone, String nickname){
        this.email=email;
        this.password=password;
        this.name=name;
        this.phone=phone;
        this.nickname=nickname;
    }

    public void encryptPassword(String encryptedPassword){
        this.password=encryptedPassword;
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .nickname(nickname)
                .build();
    }
}
