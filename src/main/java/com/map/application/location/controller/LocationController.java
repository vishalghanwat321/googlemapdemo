package com.map.application.location.controller;


/*
 *
 *  @project application
 *
 *  @author vishal on 11/07/18  10:31 PM
 *
 */

import com.map.application.location.domain.LocationDetail;
import com.map.application.location.dto.LocationDetailDTO;
import com.map.application.location.service.LocationService;
import com.map.application.path.ApiRequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")  // this resolves CORS problem
@RestController
@DependsOn("locationService")
@RequestMapping(ApiRequestPath.LOCATION)
public class LocationController {


    @Autowired
    private LocationService locationService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LocationDetailDTO setUpLocation(@RequestBody @Valid LocationDetailDTO locationDetailDTO) {

        LocationDetail locationDetail = this.locationService.setUpLocation(LocationDetailDTO.builder()
                .name(locationDetailDTO.getName())
                .description(locationDetailDTO.getDescription())
                .latitude(locationDetailDTO.getLatitude())
                .longitude(locationDetailDTO.getLongitude())
                .build());

        return LocationDetailDTO.builder()
                .name(locationDetail.getName())
                .description(locationDetail.getDescription())
                .longitude(locationDetail.getLongitude())
                .latitude(locationDetail.getLatitude())
                .build();
    }

    @GetMapping(value = "")
    public List<LocationDetailDTO> getLocations() {

        List<LocationDetailDTO> locations = new ArrayList<>();

        for (LocationDetail locationDetail : this.locationService.query()) {
            locations.add(LocationDetailDTO.builder()
                    .name(locationDetail.getName())
                    .description(locationDetail.getDescription())
                    .latitude(locationDetail.getLatitude())
                    .longitude(locationDetail.getLongitude())
                    .build());
        }

        return locations;
    }

    @GetMapping(value = "/{name}")
    public LocationDetailDTO getLocationByName(@PathVariable(value = "name") String name) {

        LocationDetail locationDetail = this.locationService.query(name);

        return LocationDetailDTO.builder()
                .name(locationDetail.getName())
                .description(locationDetail.getDescription())
                .longitude(locationDetail.getLongitude())
                .latitude(locationDetail.getLatitude())
                .build();
    }

    @PutMapping(value = "/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LocationDetailDTO updateLocation(@PathVariable(value = "name") String name, @RequestBody @Valid LocationDetailDTO locationDetailDTO) {

        LocationDetail locationDetail = this.locationService.updateLocation(name, LocationDetailDTO.builder()
                .name(locationDetailDTO.getName())
                .description(locationDetailDTO.getDescription())
                .latitude(locationDetailDTO.getLatitude())
                .longitude(locationDetailDTO.getLongitude())
                .build());

        return LocationDetailDTO.builder()
                .name(locationDetail.getName())
                .description(locationDetail.getDescription())
                .longitude(locationDetail.getLongitude())
                .latitude(locationDetail.getLatitude())
                .build();
    }

    @DeleteMapping(value = "/{name}")
    public void deleteLocationByName(@PathVariable(value = "name") String name) {
        this.locationService.deleteLocation(name);
    }

}
