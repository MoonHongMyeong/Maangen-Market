package me.moon.market.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class Location {

    private Double longitude;
    private Double latitude;

    @Builder
    public Location(Double longitude, Double latitude){
        this.longitude=longitude;
        this.latitude=latitude;
    }
}
