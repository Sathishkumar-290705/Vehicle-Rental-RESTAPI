package com.baz.vehicle.rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baz.vehicle.rent.entity.Vehicle;
import com.baz.vehicle.rent.entity.VehicleStatus;
import com.baz.vehicle.rent.entity.VehicleType;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(VehicleStatus status);

    List<Vehicle> findByType(VehicleType type);

    List<Vehicle> findByTypeAndStatus(VehicleType type, VehicleStatus status);

    boolean existsByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumberAndIdNot(String registrationNumber, Long id);
}
