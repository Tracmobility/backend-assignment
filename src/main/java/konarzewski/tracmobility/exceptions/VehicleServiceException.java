package konarzewski.tracmobility.exceptions;

/**
 * Class that implements VehicleServiceException in the API
 *
 * @author Pawel Konarzewski
 */

public class VehicleServiceException extends RuntimeException {
    public VehicleServiceException(String message) {
        super(message);
    }
}
