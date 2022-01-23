package me.moon.market.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.model.Address;
import me.moon.market.domain.model.Location;
import me.moon.market.domain.user.dto.UserUpdateRequest;
import me.moon.market.domain.user.dto.GeolocationRequest;
import me.moon.market.global.entity.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NICKNAME")
    private String nickname;

    @Embedded
    private Address address;

    @Embedded
    private Location location;

    @Builder
    public User(String email, String password, String name, String phone, String nickname){
        this.email=email;
        this.password=password;
        this.name=name;
        this.phone=phone;
        this.nickname=nickname;
    }

    public void updateMyProfile(UserUpdateRequest dto) {
        this.nickname=dto.getNickname();
        this.phone=dto.getPhone();
        this.password=dto.getPassword();
    }

    public void setUserGeolocation(GeolocationRequest dto){
        this.address= dto.toAddress();
        this.location= dto.toLocation();
    }
}
