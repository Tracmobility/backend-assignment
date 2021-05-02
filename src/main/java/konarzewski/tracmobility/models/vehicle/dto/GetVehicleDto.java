package konarzewski.tracmobility.models.vehicle.dto;

import konarzewski.tracmobility.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Class that implements response to the API end-point
 * related to listing vehicles
 *
 * @author Pawel Konarzewski
 *
 */

@Data
@AllArgsConstructor
@Builder
public class GetVehicleDto {
    Long id;
    String type;
    String qrCode;
    Status status;
}
