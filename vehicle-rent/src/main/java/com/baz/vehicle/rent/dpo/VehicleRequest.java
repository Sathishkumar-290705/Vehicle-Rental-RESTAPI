package com.baz.vehicle.rent.dpo;

import java.math.BigDecimal;

import com.baz.vehicle.rent.entity.VehicleType;

public record VehicleRequest(
        VehicleType type,
        String brand,
        String model,
        String registrationNumber,
        BigDecimal pricePerDay,
        Integer seatingCapacity) {
}
