package konarzewski.tracmobility.models.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import konarzewski.tracmobility.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Class that implements response to the API end-point
 * related to borrowing vehicle.
 *
 * @author Pawel Konarzewski
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowVehicleResponseDto {

    private String qrCode;
    private Status status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timeOfBorrowing = LocalDateTime.now();
}
