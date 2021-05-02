package konarzewski.tracmobility.repository;

import konarzewski.tracmobility.exceptions.VehicleRepositoryException;
import konarzewski.tracmobility.models.enums.Status;
import konarzewski.tracmobility.models.enums.Type;
import konarzewski.tracmobility.models.vehicle.Vehicle;
import konarzewski.tracmobility.models.vehicle.VehicleStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static konarzewski.tracmobility.fixtures.Fixtures.createListOfNoAvailableVehicles;
import static konarzewski.tracmobility.fixtures.Fixtures.createListOfVehicles;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith(SpringExtension.class)
class VehicleRepositoryTest {

    @InjectMocks
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(vehicleRepository, "vehicles", createListOfVehicles());
    }

    @Test
    @DisplayName("should return all available vehicles")
    void shouldReturnAllAvailableVehicles() {
        //given
        //when
        var result = vehicleRepository.findAvailableVehicles();
        //then
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("should return empty list if no available vehicles")
    void shouldReturnEmptyListIfNoAvailableVehicles() {
        ReflectionTestUtils.setField(vehicleRepository, "vehicles", createListOfNoAvailableVehicles());
        //when
        var result = vehicleRepository.findAvailableVehicles();
        //then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("should find vehicle by QR-code")
    void shouldFindVehicleByQrCode() {
        //given
        var qrCode = "QR_0004";
        var vehicle = Vehicle.builder()
                .id(4L)
                .type(Type.BIKE)
                .qrCode("QR_0004")
                .vehicleStatus(VehicleStatus.builder()
                        .status(Status.AVAILABLE)
                        .timeOfLastStatusChange(LocalDateTime.of(2021, 2, 20, 15, 0, 0))
                        .build())
                .build();
        //when
        var result = vehicleRepository.findByQrCode(qrCode);
        //then
        assertAll(
                () -> assertThat(result).isNotEmpty(),
                () -> assertThat(result).hasValue(vehicle)
        );
    }


    @Test
    @DisplayName("should return empty Optional if can not find vehicle with specific QR-code")
    void shouldReturnEmptyOptionalIfCanNotFindVehicleWithSpecificQrCode() {
        //given
        var qrCode = "QR_0040";
        //when
        var result = vehicleRepository.findByQrCode(qrCode);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should save new vehicle to list")
    void shouldSaveNewVehicleToList() {
        //given
        ReflectionTestUtils.setField(vehicleRepository, "vehicles", new ArrayList<Vehicle>());
        var newVehicle = Vehicle
                .builder()
                .id(10L)
                .type(Type.E_BIKE)
                .qrCode("QR_0010")
                .vehicleStatus(VehicleStatus
                        .builder()
                        .status(Status.AVAILABLE)
                        .build())
                .build();
        //when
        var result = vehicleRepository.save(newVehicle);
        //then
        assertThat(result).hasFieldOrPropertyWithValue("qrCode", "QR_0010");
    }

    @Test
    @DisplayName("should throw VehicleRepositoryException if try to save null")
    void shouldThrowVehicleRepositoryExceptionIfTryToSaveNull() {
        //given
        //when
        var thrown = catchThrowable(() -> vehicleRepository.save(null));
        //then
        assertThat(thrown)
                .isInstanceOf(VehicleRepositoryException.class)
                .hasMessageContaining("Vehicle is null");
    }

}