package konarzewski.tracmobility.models.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import konarzewski.tracmobility.models.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that implements Vehicle status structure.
 *
 * @author Pawel Konarzewski
 */

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class VehicleStatus {

    Status status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Builder.Default
    LocalDateTime timeOfLastStatusChange = LocalDateTime.now();

    /**
     * The method to check if Vehicle is available to use
     */
    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    /**
     * The method to change Vehicle status to RUNNING
     */
    public void borrowVehicle() {
        timeOfLastStatusChange = LocalDateTime.now();
        status = Status.RUNNING;
    }

    /**
     * The method to change Vehicle status to RETURN
     */
    public void returnVehicle() {
        timeOfLastStatusChange = LocalDateTime.now();
        status = Status.AVAILABLE;
    }

    /**
     * The method to return date and time of last status change
     */
    public String lastStatusChanged() {
        return timeOfLastStatusChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * The method to return actual status of vehicle
     */
    public String actualStatus() {
        return status.toString();
    }
}
