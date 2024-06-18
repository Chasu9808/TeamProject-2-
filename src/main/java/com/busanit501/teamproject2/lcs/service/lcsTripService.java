package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.dto.lcsTripDTO;
import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class lcsTripService {

    @Autowired
    private lcsTripRepository repository;

    public List<lcsTripDTO> getAllTrips() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public lcsTripDTO getTripById(Long id) {
        return repository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public lcsTripDTO saveTrip(lcsTripDTO dto) {
        lcsTrip trip = convertToEntity(dto);
        return convertToDto(repository.save(trip));
    }

    public void deleteTrip(Long id) {
        repository.deleteById(id);
    }

    private lcsTripDTO convertToDto(lcsTrip entity) {
        return lcsTripDTO.builder()
                .trip_id(entity.getTrip_id())
                .trip_name(entity.getTrip_name())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .rating(entity.getRating())
                .category(entity.getCategory())
                .recommended(entity.isRecommended())
                .build();
    }

    private lcsTrip convertToEntity(lcsTripDTO dto) {
        return lcsTrip.builder()
                .trip_id(dto.getTrip_id())
                .trip_name(dto.getTrip_name())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .rating(dto.getRating())
                .category(dto.getCategory())
                .recommended(dto.isRecommended())
                .build();
    }
}
