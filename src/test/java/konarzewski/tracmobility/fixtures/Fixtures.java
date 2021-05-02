package konarzewski.tracmobility.fixtures;

import konarzewski.tracmobility.models.vehicle.Vehicle;
import konarzewski.tracmobility.models.enums.Status;
import konarzewski.tracmobility.models.enums.Type;
import konarzewski.tracmobility.models.vehicle.VehicleStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface Fixtures {

    static List<Vehicle> createListOfVehicles() {
        return List.of(
                Vehicle.builder()
                        .id(1L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0001")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(2L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0002")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.RUNNING)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(3L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0003")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.REPAIRING)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(4L)
                        .type(Type.BIKE)
                        .qrCode("QR_0004")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .timeOfLastStatusChange(LocalDateTime.of(2021,2,20,15,0,0))
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(5L)
                        .type(Type.E_BIKE)
                        .qrCode("QR_0005")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(6L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0006")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .build())
                        .build()
        );
    }

    static List<Vehicle> createListOfNoAvailableVehicles() {
        return List.of(
                Vehicle.builder()
                        .id(1L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0001")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.RUNNING)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(2L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0002")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.RUNNING)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(3L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0003")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.REPAIRING)
                                .build())
                        .build()
        );
    }

    static List<Vehicle> createListOfAvailableVehicles() {
        return List.of(
                Vehicle.builder()
                        .id(1L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0001")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(2L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0002")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .build())
                        .build(),
                Vehicle.builder()
                        .id(3L)
                        .type(Type.SCOOTER)
                        .qrCode("QR_0003")
                        .vehicleStatus(VehicleStatus.builder()
                                .status(Status.AVAILABLE)
                                .build())
                        .build()
        );
    }


}
