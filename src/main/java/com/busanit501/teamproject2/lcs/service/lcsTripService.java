package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class lcsTripService {

    private final lcsTripRepository tripRepository;

    @Autowired
    public lcsTripService(lcsTripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void saveTrip(lcsTrip trip) {
        tripRepository.save(trip);
    }
}
