package com.chua.backendassignment.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeolocationResponse {
    private String ip;
    private String city;
    private String region;
    @JsonProperty("country_name")
    private String countryName;
    private Double latitude;
    private Double longitude;
}
