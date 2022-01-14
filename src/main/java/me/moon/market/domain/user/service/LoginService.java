package me.moon.market.domain.user.service;

import me.moon.market.global.dto.SessionUser;

public interface LoginService {

    void login(SessionUser user);

    void logout();
}
