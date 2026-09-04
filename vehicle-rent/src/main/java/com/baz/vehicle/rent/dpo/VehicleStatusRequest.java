package com.baz.vehicle.rent.dpo;

import com.baz.vehicle.rent.entity.VehicleStatus;

public class VehicleStatusRequest {

    private VehicleStatus status;

    public VehicleStatusRequest() {
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
