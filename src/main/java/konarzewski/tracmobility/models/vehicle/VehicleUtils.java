package konarzewski.tracmobility.models.vehicle;

import konarzewski.tracmobility.models.enums.Status;

import java.util.function.Predicate;

/**
 * Class that implements the Vehicle API utility methods
 *
 * @author Pawel Konarzewski
 */

public interface VehicleUtils {

    /**
     * Functional Interface created to allow filtering Vehicles by
     * availability without damaging encapsulation of class Vehicle
     */

    Predicate<Vehicle> isAvailable = vehicle -> vehicle.vehicleStatus.status == Status.AVAILABLE;
}
