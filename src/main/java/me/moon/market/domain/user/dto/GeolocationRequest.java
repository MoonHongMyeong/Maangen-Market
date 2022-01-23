package me.moon.market.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.model.Address;
import me.moon.market.domain.model.Location;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class GeolocationRequest {
    //address
    @NotEmpty
    private String state;
    @NotEmpty
    private String city;
    @NotEmpty
    private String town;
    //location
    @NotEmpty
    private Double longitude;
    @NotEmpty
    private Double latitude;

    @Builder
    public GeolocationRequest(String state, String city, String town, Double longitude, Double latitude){
        this.state=state;
        this.city=city;
        this.town=town;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public Address toAddress(){
        return Address.builder()
                .state(this.state)
                .city(this.city)
                .town(this.town)
                .build();
    }

    public Location toLocation(){
        return Location.builder()
                .longitude(this.longitude)
                .latitude(this.latitude)
                .build();
    }
}
