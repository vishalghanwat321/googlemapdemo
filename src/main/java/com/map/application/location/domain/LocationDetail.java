package com.map.application.location.domain;


/*
 *
 *  @project application
 *
 *  @author vishal on 11/07/18  10:37 PM
 *
 */


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Cacheable(false)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationDetail implements Serializable {

    @Id
    @Column(name = "name", updatable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "latitude", nullable = false)
    @Getter
    @Setter
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @Getter
    @Setter
    private Double longitude;


    @Column(name = "creation_timestamp", updatable = false, nullable = false)
    @Getter
    protected LocalDateTime creationTimestamp;

    @Column(name = "last_modification_timestamp")
    @Getter
    protected LocalDateTime lastModificationTimestamp;

    @PrePersist
    protected void onPrePersist() {
        this.creationTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onPreUpdate() {
        this.lastModificationTimestamp = LocalDateTime.now();
    }
}
