package konarzewski.tracmobility.repository;

import konarzewski.tracmobility.exceptions.VehicleRepositoryException;
import konarzewski.tracmobility.models.vehicle.Vehicle;
import konarzewski.tracmobility.models.vehicle.VehicleStatus;
import konarzewski.tracmobility.models.vehicle.VehicleUtils;
import konarzewski.tracmobility.models.enums.Status;
import konarzewski.tracmobility.models.enums.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that implements basic methods related to Vehicle Repository
 *
 * @author Pawel Konarzewski
 */

@Repository
public class VehicleRepository {

    private final List<Vehicle> vehicles = new ArrayList<>();

    public VehicleRepository() {
       /* vehicles.add(Vehicle.builder()
                .id(1L)
                .type(Type.SCOOTER)
                .qrCode("QR_0001")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.AVAILABLE)
                        .build())
                .build());
        vehicles.add(Vehicle.builder()
                .id(2L)
                .type(Type.SCOOTER)
                .qrCode("QR_0002")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.RUNNING)
                        .build())
                .build());
        vehicles.add(Vehicle.builder()
                .id(3L)
                .type(Type.SCOOTER)
                .qrCode("QR_0003")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.REPAIRING)
                        .build())
                .build());
        vehicles.add(Vehicle.builder()
                .id(4L)
                .type(Type.BIKE)
                .qrCode("QR_0004")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.AVAILABLE)
                        .build())
                .build());
        vehicles.add(Vehicle.builder()
                .id(5L)
                .type(Type.E_BIKE)
                .qrCode("QR_0005")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.AVAILABLE)
                        .build())
                .build());
        vehicles.add(Vehicle.builder()
                .id(6L)
                .type(Type.SCOOTER)
                .qrCode("QR_0006")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.AVAILABLE)
                        .build())
                .build());*/
    }

    /**
     * The method that searches through all vehicles and finds
     * only vehicles which are available to use.
     *
     * @return list of all vehicles with Status.AVAILABLE
     */

    public List<Vehicle> findAvailableVehicles() {
        return vehicles
                .stream()
                .filter(VehicleUtils.isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * The method that searches through all vehicles and finds
     * vehicle with specific QR-code
     *
     * @param qrCode QR-code of the vehicle to be searched for
     * @return list of all vehicles with Status.AVAILABLE
     */

    public Optional<Vehicle> findByQrCode(String qrCode) {
        return vehicles
                .stream()
                .filter(vehicle -> vehicle.hasQrCode(qrCode))
                .findFirst();
    }

    /**
     * The method that add vehicle to list of vehicles
     * only vehicles which are available to use.
     *
     * @param vehicle vehicle object to be saved
     * @return returns saved vehicle object
     */

    public Vehicle save(Vehicle vehicle) {
        if (vehicle == null) {
            throw new VehicleRepositoryException("Vehicle is null");
        }
        vehicles.add(vehicle);
        return vehicle;
    }
}
