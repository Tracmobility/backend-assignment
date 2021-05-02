package konarzewski.tracmobility.models.enums;

import lombok.Getter;

/**
 * Enum that classifies the vehicle status in the API.
 *
 * @author Pawel Konarzewski
 */
@Getter
public enum Status {
    AVAILABLE("AVAILABLE"),
    RUNNING("RUNNING"),
    REPAIRING("REPAIRING");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
