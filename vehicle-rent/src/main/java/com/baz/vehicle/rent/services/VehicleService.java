package com.baz.vehicle.rent.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baz.vehicle.rent.dpo.VehicleRequest;
import com.baz.vehicle.rent.dpo.VehicleResponse;
import com.baz.vehicle.rent.entity.Vehicle;
import com.baz.vehicle.rent.entity.VehicleStatus;
import com.baz.vehicle.rent.entity.VehicleType;
import com.baz.vehicle.rent.exception.DuplicateRegistrationException;
import com.baz.vehicle.rent.exception.VehicleNotFoundException;
import com.baz.vehicle.rent.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public VehicleResponse createVehicle(VehicleRequest request) {
        if (vehicleRepository.existsByRegistrationNumber(request.registrationNumber())) {
            throw new DuplicateRegistrationException(request.registrationNumber());
        }

        Vehicle vehicle = Vehicle.builder()
                .type(request.type())
                .brand(request.brand())
                .model(request.model())
                .registrationNumber(request.registrationNumber())
                .pricePerDay(request.pricePerDay())
                .seatingCapacity(request.seatingCapacity())
                .status(VehicleStatus.AVAILABLE)
                .build();

        return toResponse(vehicleRepository.save(vehicle));
    }

    @Transactional
    public VehicleResponse updateVehicle(Long id, VehicleRequest request) {
        Vehicle vehicle = findVehicle(id);

        if (vehicleRepository.existsByRegistrationNumberAndIdNot(request.registrationNumber(), id)) {
            throw new DuplicateRegistrationException(request.registrationNumber());
        }

        vehicle.setType(request.type());
        vehicle.setBrand(request.brand());
        vehicle.setModel(request.model());
        vehicle.setRegistrationNumber(request.registrationNumber());
        vehicle.setPricePerDay(request.pricePerDay());
        vehicle.setSeatingCapacity(request.seatingCapacity());

        return toResponse(vehicleRepository.save(vehicle));
    }

    @Transactional
    public void deleteVehicle(Long id) {
        vehicleRepository.delete(findVehicle(id));
    }

    @Transactional(readOnly = true)
    public VehicleResponse getVehicleById(Long id) {
        return toResponse(findVehicle(id));
    }

    @Transactional(readOnly = true)
    public List<VehicleResponse> getVehicles(VehicleType type, VehicleStatus status) {
        List<Vehicle> vehicles;
        if (type != null && status != null) {
            vehicles = vehicleRepository.findByTypeAndStatus(type, status);
        } else if (type != null) {
            vehicles = vehicleRepository.findByType(type);
        } else if (status != null) {
            vehicles = vehicleRepository.findByStatus(status);
        } else {
            vehicles = vehicleRepository.findAll();
        }
        return vehicles.stream().map(this::toResponse).toList();
    }

    private Vehicle findVehicle(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }

    private VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getRegistrationNumber(),
                vehicle.getPricePerDay(),
                vehicle.getSeatingCapacity(),
                vehicle.getStatus(),
                vehicle.getCreatedAt());
    }
}
