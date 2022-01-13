package me.moon.market.domain.user.dao;

import me.moon.market.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
