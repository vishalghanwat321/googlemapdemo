package com.map.application.location.dto;


/*
 *
 *  @project application
 *
 *  @author vishal on 11/07/18  10:31 PM
 *
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;


@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDetailDTO {

    @NotNull
    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "latitude", required = true, defaultValue = "0.0")
    private Double latitude;

    @JsonProperty(value = "longitude", required = true, defaultValue = "0.0" )
    private Double longitude;

}
