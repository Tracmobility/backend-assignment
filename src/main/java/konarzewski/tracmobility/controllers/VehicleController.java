package konarzewski.tracmobility.controllers;

import konarzewski.tracmobility.models.general.dto.ResponseDataDto;
import konarzewski.tracmobility.models.vehicle.dto.BorrowVehicleResponseDto;
import konarzewski.tracmobility.models.vehicle.dto.GetVehicleDto;
import konarzewski.tracmobility.models.vehicle.dto.ReturnVehicleResponseDto;
import konarzewski.tracmobility.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController that creates service end-points related to the vehicle.
 *
 * @author Pawel Konarzewski
 */

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * The method that searches for all vehicles available to use.
     *
     * @return ResponseDataDto object with list of available vehicles and
     * Http status code 200 - ok.
     */

    @GetMapping("/available")
    public ResponseDataDto<List<GetVehicleDto>> getAllAvailableVehicles() {

        return ResponseDataDto.
                <List<GetVehicleDto>>builder()
                .data(vehicleService.findAvailableVehicles())
                .build();
    }

    /**
     * The method used to borrow the vehicle.
     * It finds the vehicle by QR-code and changes its status to RUNNING.
     *
     * @return ResponseDataDto object with list of available vehicles and
     * Http status code 200 - ok.
     */

    @PatchMapping("/borrow/{qrCode}")
    public ResponseDataDto<BorrowVehicleResponseDto> borrowVehicle(@PathVariable String qrCode) {

        return ResponseDataDto.
                <BorrowVehicleResponseDto>builder()
                .data(vehicleService.borrowVehicle(qrCode))
                .build();
    }

    /**
     * The method used to return the vehicle.
     * It finds the vehicle by QR-code and changes its status to AVAILABLE.
     *
     * @return ResponseDataDto object with list of available vehicles and
     * Http status code 200 - ok.
     */

    @PatchMapping("/return/{qrCode}")
    public ResponseDataDto<ReturnVehicleResponseDto> returnVehicle(@PathVariable String qrCode) {

        return ResponseDataDto.
                <ReturnVehicleResponseDto>builder()
                .data(vehicleService.returnVehicle(qrCode))
                .build();
    }
}
