package konarzewski.tracmobility.services;

import konarzewski.tracmobility.exceptions.VehicleNotFoundException;
import konarzewski.tracmobility.exceptions.VehicleServiceException;
import konarzewski.tracmobility.models.vehicle.Vehicle;
import konarzewski.tracmobility.models.vehicle.dto.BorrowVehicleResponseDto;
import konarzewski.tracmobility.models.vehicle.dto.GetVehicleDto;
import konarzewski.tracmobility.models.vehicle.dto.ReturnVehicleResponseDto;
import konarzewski.tracmobility.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements the vehicle service methods
 *
 * @author Pawel Konarzewski
 */

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    /**
     * The method that searches for all vehicles with status AVAILABLE.
     *
     * @return list of available vehicles.
     * @throws VehicleServiceException if all vehicles are not available.
     */

    public synchronized List<GetVehicleDto> findAvailableVehicles() {
        var availableVehicles = vehicleRepository
                .findAvailableVehicles()
                .stream()
                .map(Vehicle::toGetVehicleDto)
                .collect(Collectors.toList());

        if (availableVehicles.isEmpty()) {
            throw new VehicleServiceException("No available vehicles");
        }

        return availableVehicles;
    }

    /**
     * The method that borrows a specific vehicle from a repository
     *
     * @param qrCode QR-code of the vehicle to be searched for
     * @return dto of borrowed vehicle with unique QR-code and status RUNNING
     * @throws VehicleServiceException if the vehicle is not available to borrow. Include message
     *                                 with date and time about last time when the vehicle was used and its QR-code.
     */

    public synchronized BorrowVehicleResponseDto borrowVehicle(String qrCode) {
        var vehicle = findVehicleByQrCode(qrCode);

        if (!vehicle.isAvailable()) {
            throw new VehicleServiceException(
                    "Vehicle with QR-code: " + qrCode + ", is not available. " +
                            "Vehicle status: " + vehicle.getActualStatus() +
                            ", Last status change: " + vehicle.getLastStatusChanged());
        }

        vehicle.borrowVehicle();
        return vehicleRepository
                .save(vehicle)
                .toBorrowVehicleResponseDto();
    }

    /**
     * The method that returns a specific vehicle back to the repository
     *
     * @param qrCode QR-code of the vehicle to be searched for
     * @return dto of the returned vehicle with unique QR-code and status AVAILABLE
     * @throws VehicleNotFoundException if vehicle is not available.
     */

    public synchronized ReturnVehicleResponseDto returnVehicle(String qrCode) {
        var vehicle = findVehicleByQrCode(qrCode);
        vehicle.returnVehicle();
        vehicleRepository.save(vehicle);
        return vehicle.toReturnVehicleResponseDto();
    }

    /**
     * The method that searches for a vehicle with a specific QR-code.
     *
     * @param qrCode QR-code of the vehicle to be searched for
     * @return vehicle with a specific QR-code
     * @throws VehicleNotFoundException if vehicle is not available.
     */

    private Vehicle findVehicleByQrCode(String qrCode) {
        return vehicleRepository
                .findByQrCode(qrCode)
                .orElseThrow(() -> new VehicleNotFoundException("Can not find vehicle with QR-code: " + qrCode));
    }
}
