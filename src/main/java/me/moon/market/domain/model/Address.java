package me.moon.market.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {
    private String state;
    private String city;
    private String town;

    @Builder
    public Address(String state, String city, String town){
        this.state=state;
        this.city=city;
        this.town=town;
    }
}
