package konarzewski.tracmobility.exceptions;

/**
 * Class that implements VehicleRepositoryException in the API
 *
 * @author Pawel Konarzewski
 */

public class VehicleRepositoryException extends RuntimeException {
    public VehicleRepositoryException(String message) {
        super(message);
    }
}
