package me.moon.market.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    private String password;
    private String phone;
    private String nickname;

    @Builder
    public UserUpdateRequest(String password, String phone, String nickname){
        this.password=password;
        this.phone=phone;
        this.nickname=nickname;
    }
}
