package me.moon.market.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.user.entity.User;

@Getter
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String nickname;

    public UserResponse(User user){
        this.id=user.getId();
        this.nickname=user.getNickname();
    }
}
