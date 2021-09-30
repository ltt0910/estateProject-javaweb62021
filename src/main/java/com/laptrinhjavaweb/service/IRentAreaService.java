package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.reponse.RentAreaDTO;

public interface IRentAreaService {
    void addRentArea(RentAreaDTO rentAreaDTO);
    void deleteRentArea(Long buildingId);
}
