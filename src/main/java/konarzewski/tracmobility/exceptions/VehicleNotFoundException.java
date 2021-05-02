package konarzewski.tracmobility.exceptions;

/**
 * Class that implements VehicleNotFoundException in the API
 *
 * @author Pawel Konarzewski
 */
public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
