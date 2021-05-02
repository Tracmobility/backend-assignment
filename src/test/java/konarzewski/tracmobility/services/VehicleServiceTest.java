package konarzewski.tracmobility.services;

import konarzewski.tracmobility.exceptions.VehicleNotFoundException;
import konarzewski.tracmobility.exceptions.VehicleRepositoryException;
import konarzewski.tracmobility.exceptions.VehicleServiceException;
import konarzewski.tracmobility.fixtures.Fixtures;
import konarzewski.tracmobility.models.enums.Status;
import konarzewski.tracmobility.models.enums.Type;
import konarzewski.tracmobility.models.vehicle.Vehicle;
import konarzewski.tracmobility.models.vehicle.VehicleStatus;
import konarzewski.tracmobility.repository.VehicleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static konarzewski.tracmobility.fixtures.Fixtures.createListOfAvailableVehicles;
import static konarzewski.tracmobility.fixtures.Fixtures.createListOfNoAvailableVehicles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository repository;

    @Test
    @DisplayName("should return all available vehicles")
    void shouldReturnAllAvailableVehicles() {
        //given
        var vehicles = createListOfAvailableVehicles();
        when(repository.findAvailableVehicles()).thenReturn(vehicles);
        //when
        var result = vehicleService.findAvailableVehicles();
        //then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(3),
                () -> assertThat(result.get(1)).hasFieldOrPropertyWithValue("qrCode","QR_0002"),
                () -> assertThat(result.get(1)).hasFieldOrPropertyWithValue("id",2L),
                () -> assertThat(result.get(1)).hasFieldOrPropertyWithValue("type", "SCOOTER"),
                () -> assertThat(result.get(1)).hasFieldOrPropertyWithValue("status", Status.AVAILABLE)

        );
    }

    @Test
    @DisplayName("should throw VehicleServiceException if no available vehicles and list is empty")
    void shouldThrowVehicleServiceExceptionIfNoAvailableVehicles() {
        //given
        when(repository.findAvailableVehicles()).thenReturn(List.of());
        //when
        var thrown = catchThrowable(() -> vehicleService.findAvailableVehicles());
        //then
        assertThat(thrown)
                .isInstanceOf(VehicleServiceException.class)
                .hasMessageContaining("No available vehicles");
    }

    @Test
    @DisplayName("should return response for borrowed vehicle ")
    void shouldReturnResponseForBorrowedVehicle() {
        //given
        var vehicle = Vehicle
                .builder()
                .qrCode("QR_0010")
                .vehicleStatus(VehicleStatus
                        .builder()
                        .status(Status.AVAILABLE)
                        .build())
                .build();
        when(repository.findByQrCode(any())).thenReturn(Optional.of(vehicle));
        when(repository.save(any())).thenReturn(vehicle);
        //when
        var result = vehicleService.borrowVehicle("");
        //then
        assertAll(
                () ->  assertThat(result).hasFieldOrPropertyWithValue("qrCode","QR_0010"),
                () -> assertThat(result).hasFieldOrPropertyWithValue("status", Status.RUNNING)
        );
    }

    @Test
    @DisplayName("should throw VehicleServiceException if specific vehicle is not available ")
    void shouldThrowVehicleServiceExceptionIfSpecificVehicleIsNotAvailable() {
        //given
        var vehicle = Vehicle
                .builder()
                .qrCode("QR_0010")
                .vehicleStatus(VehicleStatus
                        .builder()
                        .status(Status.RUNNING)
                        .build())
                .build();
        when(repository.findByQrCode(any())).thenReturn(Optional.of(vehicle));
        //when
        var thrown = catchThrowable(() -> vehicleService.borrowVehicle(""));
        //then
        assertThat(thrown)
                .isInstanceOf(VehicleServiceException.class)
                .hasMessageContaining("Vehicle with QR-code:")
                .hasMessageContaining("Vehicle status: ")
                .hasMessageContaining("Last status change: ");
    }

    @Test
    @DisplayName("should return response for returned vehicle")
    void shouldReturnResponseForReturnedVehicle() {
        //given
        var vehicle = Vehicle
                .builder()
                .qrCode("QR_0010")
                .vehicleStatus(VehicleStatus
                        .builder()
                        .status(Status.RUNNING)
                        .build())
                .build();
        when(repository.findByQrCode(any())).thenReturn(Optional.of(vehicle));
        when(repository.save(any())).thenReturn(vehicle);
        //when
        var result = vehicleService.returnVehicle("");
        //then
        assertAll(
                () ->  assertThat(result).hasFieldOrPropertyWithValue("qrCode","QR_0010"),
                () -> assertThat(result).hasFieldOrPropertyWithValue("status", Status.AVAILABLE)
        );
    }

    @Test
    @DisplayName("should throw VehicleNotFoundException when can not find Qr-Code")
    void shouldThrowVehicleNotFoundExceptionWhenCanNotFindQrCode() {
        //given
        var qrCode = "QR_00040";
        //when
        var thrown = catchThrowable(() -> vehicleService.returnVehicle(qrCode));
        //then
        assertThat(thrown).isInstanceOf(VehicleNotFoundException.class)
                .hasMessageContaining("Can not find vehicle with QR-code:");
    }
}