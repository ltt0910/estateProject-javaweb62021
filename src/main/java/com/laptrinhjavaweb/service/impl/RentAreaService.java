package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.reponse.RentAreaDTO;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RentAreaService implements IRentAreaService {
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Override
    @Transactional
    public void addRentArea(RentAreaDTO rentAreaDTO) {
        rentAreaRepository.addRentArea(rentAreaConverter.convertToEntity(rentAreaDTO));
    }

    @Override
    @Transactional
    public void deleteRentArea(Long buildingId) {
        rentAreaRepository.deleteRentArea(buildingId);
    }
}
