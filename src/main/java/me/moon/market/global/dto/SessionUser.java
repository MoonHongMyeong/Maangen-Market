package me.moon.market.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.user.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String email;
    private String name;
    private String phone;
    private String nickname;

    public SessionUser(User user){
        this.id=user.getId();
        this.email=user.getEmail();
        this.name=user.getName();
        this.phone=user.getPhone();
        this.nickname=user.getNickname();
    }
}
