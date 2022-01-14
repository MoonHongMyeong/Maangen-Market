package me.moon.market.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.entity.User;
import me.moon.market.global.dto.SessionUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{

    private final HttpSession session;

    @Override
    public void login(SessionUser user) {
        session.setAttribute("user", user);
    }

    @Override
    public void logout() {
        session.removeAttribute("user");
    }
}
