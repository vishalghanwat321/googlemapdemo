package com.map.application.location.repositories;


/*
 *
 *  @project application
 *
 *  @author vishal on 11/07/18  10:50 PM
 *
 */


import com.map.application.location.domain.LocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationDetailRepository extends JpaRepository<LocationDetail, String> {


}
