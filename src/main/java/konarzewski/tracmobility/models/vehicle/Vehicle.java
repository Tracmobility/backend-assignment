package konarzewski.tracmobility.models.vehicle;

import konarzewski.tracmobility.models.vehicle.dto.BorrowVehicleResponseDto;
import konarzewski.tracmobility.models.vehicle.dto.GetVehicleDto;
import konarzewski.tracmobility.models.vehicle.dto.ReturnVehicleResponseDto;
import konarzewski.tracmobility.models.enums.Type;
import lombok.*;


/**
 * Class that implements Vehicle structure.
 *
 * @author Pawel Konarzewski
 */

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    Long id;
    Type type;
    String qrCode;
    VehicleStatus vehicleStatus;

    /**
     * The method to check if vehicle has specific QR code.
     *
     * @param qrCode - unique code of vehicle
     * @return true if vehicle has specific QR code, false otherwise.
     */

    public boolean hasQrCode(String qrCode) {
        return this.qrCode.equals(qrCode);
    }

    /**
     * The method to check Vehicle availability
     */
    public boolean isAvailable() {
        return vehicleStatus.isAvailable();
    }

    /**
     * The method to borrow Vehicle
     */
    public void borrowVehicle() {
        vehicleStatus.borrowVehicle();
    }

    /**
     * The method to return Vehicle
     */
    public void returnVehicle() {
        vehicleStatus.returnVehicle();
    }

    /**
     * The method to return date and time of last status change
     */
    public String getLastStatusChanged() {
        return vehicleStatus.lastStatusChanged();
    }

    /**
     * The method to return actual status of vehicle
     */
    public String getActualStatus() {
        return vehicleStatus.actualStatus();
    }

    /**
     * The method to map Vehicle object to dto response for
     * service end-point related to listing all vehicles
     */

    public GetVehicleDto toGetVehicleDto() {
        return GetVehicleDto.builder()
                .id(id)
                .type(type.getType())
                .qrCode(qrCode)
                .status(vehicleStatus.status)
                .build();
    }

    /**
     * The method to map Vehicle object to dto response for
     * service end-points related to borrowing vehicle
     */

    public BorrowVehicleResponseDto toBorrowVehicleResponseDto() {
        return BorrowVehicleResponseDto
                .builder()
                .qrCode(qrCode)
                .status(vehicleStatus.status)
                .build();
    }

    /**
     * The method to map Vehicle object to dto response for
     * service end-point related to returning vehicle
     */

    public ReturnVehicleResponseDto toReturnVehicleResponseDto() {
        return ReturnVehicleResponseDto
                .builder()
                .qrCode(qrCode)
                .status(vehicleStatus.status)
                .build();
    }


}
