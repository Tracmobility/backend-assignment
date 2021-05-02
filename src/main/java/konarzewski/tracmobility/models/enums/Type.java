package konarzewski.tracmobility.models.enums;

import lombok.Getter;

/**
 * Enum that classifies the vehicle type in the API.
 *
 * @author Pawel Konarzewski
 */

@Getter
public enum Type {
    SCOOTER("SCOOTER"),
    BIKE("BIKE"),
    E_BIKE("E-BIKE");

    private final String type;

    Type(String type) {
        this.type = type;
    }
}
