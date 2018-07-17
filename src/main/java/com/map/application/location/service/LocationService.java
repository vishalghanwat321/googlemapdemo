package com.map.application.location.service;


/*
 *
 *  @project application
 *
 *  @author vishal on 11/07/18  10:48 PM
 *
 */

import com.map.application.location.domain.LocationDetail;
import com.map.application.location.dto.LocationDetailDTO;
import com.map.application.location.exception.ItemNotFoundException;
import com.map.application.location.repositories.LocationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class LocationService {

    @Autowired
    private LocationDetailRepository locationDetailRepository;

    @Transactional(readOnly = true)
    public Iterable<LocationDetail> query() {
        return this.locationDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LocationDetail query(String name) throws IllegalArgumentException, ItemNotFoundException {
        if (Objects.isNull(name))
            throw new IllegalArgumentException("Failed to query Location Detail (reason: invalid name).");

        if (!(this.locationDetailRepository.existsById(name)))
            throw new ItemNotFoundException("Location not found.");

        LocationDetail locationDetail = this.locationDetailRepository.findById(name).get();
        if (Objects.isNull(locationDetail))
            throw new ItemNotFoundException("Location not found.");
        return locationDetail;
    }

    @Transactional
    public LocationDetail setUpLocation(LocationDetailDTO request) {
        if (Objects.isNull(request))
            throw new IllegalArgumentException("Failed to instantiate Location Detail (reason: invalid Location Detail).");

        return this.locationDetailRepository.save(LocationDetail.builder()
                .name(request.getName())
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build());
    }

    @Transactional
    public LocationDetail updateLocation(String name, LocationDetailDTO request)
            throws IllegalArgumentException, ItemNotFoundException {
        if (Objects.isNull(request))
            throw new IllegalArgumentException("Failed to update Location Detail (reason: invalid request).");

        LocationDetail locationDetail = this.query(name);

        locationDetail.setDescription(request.getDescription());
        locationDetail.setLatitude(request.getLatitude());
        locationDetail.setLongitude(request.getLongitude());

        return this.locationDetailRepository.save(locationDetail);
    }

    @Transactional
    public void deleteLocation(String name)
            throws IllegalArgumentException, ItemNotFoundException {
        if (Objects.isNull(name))
            throw new IllegalArgumentException("Failed to delete Location Detail (reason: invalid request).");
        if (!(this.locationDetailRepository.existsById(name)))
            throw new ItemNotFoundException("Location not found.");

        this.locationDetailRepository.deleteById(name);
    }

}
