package com.baz.vehicle.rent.dpo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baz.vehicle.rent.entity.VehicleStatus;
import com.baz.vehicle.rent.entity.VehicleType;

public record VehicleResponse(
        Long id,
        VehicleType type,
        String brand,
        String model,
        String registrationNumber,
        BigDecimal pricePerDay,
        Integer seatingCapacity,
        VehicleStatus status,
        LocalDateTime createdAt) {
}
